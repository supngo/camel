package com.naturecode.camelA.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import com.naturecode.camelA.models.ArrayListAggregationStrategy;
import com.naturecode.camelA.models.CurrencyExchange;

@Component
public class AggregateSendRouter extends RouteBuilder{

  @Override
  public void configure() throws Exception {
    getContext().setTracing(true);
    errorHandler(deadLetterChannel("activemq:dead-letter-queue"));

    from("file:files/json")
    .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
    .aggregate(simple("${body.to}"), new ArrayListAggregationStrategy())
    .completionSize(3)
    .wireTap("log:wire-tap") // send mesg to additional receiver
    .to("log:aggregate-json");
  }
}

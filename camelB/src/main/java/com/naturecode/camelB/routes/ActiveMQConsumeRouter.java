package com.naturecode.camelB.routes;

import java.math.BigDecimal;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.naturecode.camelB.models.CurrencyExchange;

// @Component
public class ActiveMQConsumeRouter extends RouteBuilder{
  @Autowired
  CurrencyExchangeTransform currencyExchangeTransform;

  @Override
  public void configure() throws Exception {
    from("activemq:nature-queue")
    .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
    .bean(currencyExchangeTransform)
    .to("log: received message");
  }  
}

@Component
class CurrencyExchangeTransform {
  public CurrencyExchange process(CurrencyExchange currencyExchange) {
    currencyExchange.setConversionMultiple(currencyExchange.getConversionMultiple().multiply(BigDecimal.TEN));
    return currencyExchange;
  }
}

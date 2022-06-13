package com.naturecode.camelA.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SplitSendRouter extends RouteBuilder {
  @Override
  public void configure() throws Exception {
    from("file:files/csv")
    .unmarshal().csv()
    .split(body())
    .to("activemq:split-queue");
  }
}

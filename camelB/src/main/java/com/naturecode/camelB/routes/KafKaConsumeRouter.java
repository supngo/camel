package com.naturecode.camelB.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

// @Component
public class KafKaConsumeRouter extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from("kafka:nature-kafka")
    .log("${body}")
    // .to("kafka:nature-kafka");
    .to("log:received kafka mesg");
  }
}
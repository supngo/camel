package com.naturecode.camelA.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

// @Component
public class ActiveMQSendRouter extends RouteBuilder {
  @Override
  public void configure() throws Exception {
    // from("timer:active-mq?period=10000")
    from("file:files/json")
    // .transform().constant("My mesg")
    .log("${body}")
    .to("activemq:nature-queue");    
  }
}

package com.naturecode.camelB.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SplitReceiveRouter extends RouteBuilder{

  @Override
  public void configure() throws Exception {
    from("activemq:split-queue")
    .to("log:splitted-messages");
  }
  
}

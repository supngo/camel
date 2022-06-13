package com.naturecode.camelB.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

// @Component
public class RestConsumeRouter extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    restConfiguration().host("localhost").port(8080);

    from("timer:rest-consumer?period=10000")
    .setHeader("from", () -> "USD")
    .setHeader("to", () -> "ETH")
    .to("rest:get:/currency-exchange/{from}/{to}")
    .log("${body}");
  }  
}

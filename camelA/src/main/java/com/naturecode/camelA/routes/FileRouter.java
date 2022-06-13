package com.naturecode.camelA.routes;

import java.util.Map;

import org.apache.camel.Header;
import org.apache.camel.Headers;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

// @Component
public class FileRouter extends RouteBuilder {
  @Autowired
  private DeciderBean deciderBean;

  @Override
  public void configure() throws Exception {
    // Patterns: 
    // pipeline (from - transform - process - to)
    // content based routing (choice)
    // multicast (multicast)
    // split (split)


    from("file:files/input")
    .routeId("fileId")
    .transform().body(String.class)
    .choice() // content bases routing
      .when(simple("${file:ext} ends with 'xml'"))
        .log("XML file")
      .when(method(deciderBean))
      // .when(simple("${body} contains 'USD'"))
        .log("not XML file but contains USD")
      .otherwise()
        .log("other file type")
    .end()
    .log("${body}")
    .to("file:files/output");    
  }
}

@Component
@Log
class DeciderBean {
  public boolean checkThis(String body, @Headers Map<String, String> headers) {
    log.info("deciderBean decide: " + body + "\n" + headers);
    return true;
  }
}

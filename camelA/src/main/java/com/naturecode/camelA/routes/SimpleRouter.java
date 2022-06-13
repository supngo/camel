package com.naturecode.camelA.routes;

import java.time.LocalDateTime;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

// @Component
public class SimpleRouter extends RouteBuilder {
  @Autowired
  private CurrentTimeBean currentTimeBean;

  @Autowired
  private SimpleLogger simpleLogger;

  @Override
  public void configure() throws Exception {
    // queue/timer
    // transform
    // save/log
    from("timer:first-timer")
    // .transform().constant("Time: " + LocalDateTime.now())
    .bean(currentTimeBean, "getCurrentTime")
    .bean(simpleLogger)
    .to("log:first-timer");
  }
}

@Component
class CurrentTimeBean {
  public String getCurrentTime() {
    return "Time now is: " + LocalDateTime.now();
  }
}

// @Component
@Log
class SimpleLogger {
  public void process(String mesg) {
    log.info("-> " + mesg);
  }
}

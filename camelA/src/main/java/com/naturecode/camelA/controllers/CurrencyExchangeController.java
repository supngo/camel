package com.naturecode.camelA.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.naturecode.camelA.models.CurrencyExchange;

import lombok.extern.java.Log;

import java.math.BigDecimal;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@Log
public class CurrencyExchangeController {
  @GetMapping(value="/currency-exchange/{from}/{to}")
  public CurrencyExchange getCurEx(@PathVariable String from, @PathVariable String to) {
    log.info("getCurEx is called...");
    return new CurrencyExchange(Long.valueOf(new Random().nextLong(100)), from, to, BigDecimal.valueOf(50));
  }
}

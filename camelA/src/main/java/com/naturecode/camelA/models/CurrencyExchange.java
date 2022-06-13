package com.naturecode.camelA.models;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyExchange {
  private Long id;
  private String from;
  private String to;
  private BigDecimal conversionMultiple;
}
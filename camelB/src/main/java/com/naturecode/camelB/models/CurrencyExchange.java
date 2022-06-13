package com.naturecode.camelB.models;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
public class CurrencyExchange {
  private Long id;
  private String from;
  private String to;
  private BigDecimal conversionMultiple;
}

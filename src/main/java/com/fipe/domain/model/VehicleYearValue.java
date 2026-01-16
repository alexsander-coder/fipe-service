package com.fipe.domain.model;

import java.math.BigDecimal;

public class VehicleYearValue {

  private final Integer year;
  private final BigDecimal value;
  private BigDecimal variationValue;
  private BigDecimal variationPercent;

  public VehicleYearValue(Integer year, BigDecimal value) {
    this.year = year;
    this.value = value;
  }

  public Integer getYear() {
    return year;
  }

  public BigDecimal getValue() {
    return value;
  }

  public BigDecimal getVariationValue() {
    return variationValue;
  }

  public BigDecimal getVariationPercent() {
    return variationPercent;
  }

  public void setVariationValue(BigDecimal variationValue) {
    this.variationValue = variationValue;
  }

  public void setVariationPercent(BigDecimal variationPercent) {
    this.variationPercent = variationPercent;
  }
}
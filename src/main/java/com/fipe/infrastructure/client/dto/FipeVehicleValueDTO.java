package com.fipe.infrastructure.client.dto;

import java.math.BigDecimal;

public class FipeVehicleValueDTO {

  private String price;
  private Integer modelYear;

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public Integer getModelYear() {
    return modelYear;
  }

  public void setModelYear(Integer modelYear) {
    this.modelYear = modelYear;
  }

  public BigDecimal getPriceAsBigDecimal() {
    if (price == null) {
      return BigDecimal.ZERO;
    }

    String normalized = price
        .replace("R$", "")
        .replace(".", "")
        .replace(",", ".")
        .trim();

    return new BigDecimal(normalized);
  }
}

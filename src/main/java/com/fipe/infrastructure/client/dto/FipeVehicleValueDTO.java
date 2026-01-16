package com.fipe.infrastructure.client.dto;

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
}

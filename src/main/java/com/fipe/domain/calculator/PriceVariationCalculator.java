package com.fipe.domain.calculator;

import com.fipe.domain.model.VehicleYearValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class PriceVariationCalculator {

  public void calculate(List<VehicleYearValue> values) {

    for (int i = 0; i < values.size() - 1; i++) {

      VehicleYearValue current = values.get(i);
      VehicleYearValue previous = values.get(i + 1);

      BigDecimal variationValue = current.getValue().subtract(previous.getValue());

      BigDecimal variationPercent = variationValue
          .divide(previous.getValue(), 4, RoundingMode.HALF_UP)
          .multiply(BigDecimal.valueOf(100));

      current.setVariationValue(variationValue);
      current.setVariationPercent(variationPercent);
    }
  }
}
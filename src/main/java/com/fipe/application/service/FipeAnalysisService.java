package com.fipe.application.service;

import com.fipe.domain.calculator.PriceVariationCalculator;
import com.fipe.domain.model.VehicleYearValue;
import com.fipe.infrastructure.client.FipeClient;
import com.fipe.infrastructure.client.dto.FipeVehicleValueDTO;
import com.fipe.infrastructure.client.dto.FipeYearDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FipeAnalysisService {

  private final FipeClient fipeClient;
  private final PriceVariationCalculator calculator;

  public FipeAnalysisService(FipeClient fipeClient) {
    this.fipeClient = fipeClient;
    this.calculator = new PriceVariationCalculator();
  }

  public List<VehicleYearValue> analisar(
      String vehicleType,
      String brandId,
      String modelId) {

    // 1) Buscar anos disponíveis
    List<FipeYearDTO> years = fipeClient.listarAnos(vehicleType, brandId, modelId);

    // 2) Converter para domínio (VehicleYearValue)
    List<VehicleYearValue> values = years.stream()
        .map(yearDto -> {
          Integer year = extrairAno(yearDto);
          FipeVehicleValueDTO valueDto = fipeClient.buscarValorPorAno(
              vehicleType,
              brandId,
              modelId,
              yearDto.getCode());

          BigDecimal price = valueDto.getPriceAsBigDecimal();
          return new VehicleYearValue(year, price);
        })
        // 3) Ordenar do mais recente para o mais antigo
        .sorted(Comparator.comparing(VehicleYearValue::getYear).reversed())
        .collect(Collectors.toList());

    // 4) Calcular variações
    calculator.calculate(values);

    return values;
  }

  private Integer extrairAno(FipeYearDTO dto) {
    // Ex.: "2014-5" → 2014
    return Integer.parseInt(dto.getCode().substring(0, 4));
  }
}

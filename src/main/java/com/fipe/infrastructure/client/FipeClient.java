package com.fipe.infrastructure.client;

import com.fipe.infrastructure.client.dto.FipeVehicleValueDTO;
import com.fipe.infrastructure.client.dto.FipeYearDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class FipeClient {

  private static final String BASE_URL = "https://fipe.parallelum.com.br/api/v2";

  private final WebClient webClient;

  public FipeClient(WebClient.Builder builder) {
    this.webClient = builder.baseUrl(BASE_URL).build();
  }

  public List<FipeYearDTO> listarAnos(
      String vehicleType,
      String brandId,
      String modelId) {
    return webClient.get()
        .uri("/{vehicleType}/brands/{brandId}/models/{modelId}/years",
            vehicleType, brandId, modelId)
        .retrieve()
        .bodyToFlux(FipeYearDTO.class)
        .collectList()
        .block();
  }

  public FipeVehicleValueDTO buscarValorPorAno(
      String vehicleType,
      String brandId,
      String modelId,
      String yearCode) {
    return webClient.get()
        .uri("/{vehicleType}/brands/{brandId}/models/{modelId}/years/{yearCode}",
            vehicleType, brandId, modelId, yearCode)
        .retrieve()
        .bodyToMono(FipeVehicleValueDTO.class)
        .block();
  }
}

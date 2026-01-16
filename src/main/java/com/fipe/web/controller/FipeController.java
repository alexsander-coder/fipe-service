package com.fipe.web.controller;

import com.fipe.application.service.FipeAnalysisService;
import com.fipe.domain.model.VehicleYearValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fipe")
public class FipeController {

  private final FipeAnalysisService service;

  public FipeController(FipeAnalysisService service) {
    this.service = service;
  }

  @GetMapping("/analise")
  public List<VehicleYearValue> analisar(
      @RequestParam String vehicleType,
      @RequestParam String brandId,
      @RequestParam String modelId) {

    return service.analisar(vehicleType, brandId, modelId);
  }
}
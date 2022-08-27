package com.realo.estate.integration.service;

import com.realo.estate.domain.estate.EstateType;
import com.realo.estate.dto.EstateDto;
import com.realo.estate.dto.EstateFilter;
import com.realo.estate.integration.TestcontainersTest;
import com.realo.estate.service.EstateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EstateServiceIT extends TestcontainersTest {

  private static final String A_100_DEVELOPMENT = "A100 Development";

  @Autowired
  private EstateService estateService;

  @Test
  @DisplayName("find all by estate type default test case")
  void findAllByEstateTypeTestCase() {
    List<EstateDto> livings = estateService.findAllByEstateType(EstateType.COUNTRY);
    assertThat(livings).hasSize(2);
  }

  @Test
  @DisplayName("find all by filter default test case")
  void findAllByFilter() {
    EstateFilter estateFilter = EstateFilter.builder()
        .providerName(A_100_DEVELOPMENT)
        .build();
    List<EstateDto> allByFilter = estateService.findAll(estateFilter);
    assertThat(allByFilter).hasSize(2);
  }
}
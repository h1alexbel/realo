package com.realo.estate.integration.service;

import com.realo.estate.domain.dto.EstateDto;
import com.realo.estate.domain.persistence.estate.EstateType;
import com.realo.estate.integration.TestcontainersTest;
import com.realo.estate.repository.filter.EstateFilter;
import com.realo.estate.service.EstateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EstateServiceIT extends TestcontainersTest {

    @Autowired
    private EstateService estateService;
    private static final String A_100_DEVELOPMENT = "A100 Development";

    @Test
    @DisplayName("update estate type by id default test case")
    void updateEstateTypeTestCase() {
        EstateDto estateDto = estateService.findById(1L);
        estateService.updateEstateTypeById(EstateType.COMMERCIAL, estateDto.getId());
        List<EstateDto> commercials = estateService.findAllByEstateType(EstateType.COMMERCIAL);
        assertThat(commercials).hasSize(6);
    }

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
package com.realo.estate.integration;

import com.realo.estate.domain.dto.ProviderDto;
import com.realo.estate.exception.ResourceNotFoundException;
import com.realo.estate.service.ProviderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProviderServiceIT extends TestcontainersTest {

    @Autowired
    private ProviderService providerService;
    private static final String WE_CAPITAL = "WE Capital";
    private static final String A100_DEVELOPMENT = "A100 Development";

    @Test
    @DisplayName("save Provider default test case")
    void saveProviderTestCase() {
        ProviderDto providerDto = ProviderDto.builder()
                .name(WE_CAPITAL)
                .build();
        ProviderDto save = providerService.save(providerDto);
        assertThat(save.getId()).isNotNull();
    }

    @Test
    @DisplayName("find by name default test case")
    void findByNameTestCase() {
        ProviderDto found = providerService.findByName(A100_DEVELOPMENT);
        assertThat(found).isNotNull();
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("find by name null test case")
    void findByNameNullCase(String name) {
        assertThrows(ResourceNotFoundException.class,
                () -> providerService.findByName(name));
    }
}
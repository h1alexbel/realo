package com.realo.estate.domain.mapper;

import com.realo.estate.domain.dto.ProviderDto;
import com.realo.estate.domain.persistence.estate.Provider;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProviderMapperTest {

    @Autowired
    private ProviderMapper providerMapper;
    private static final String NAME = "Local";
    private static final String WEBSITE_LINK = "https://local.by";

    @Test
    @DisplayName("map to dto from entity default test case")
    @Disabled
    void mapToDtoTestCase() {
        Provider provider = Provider.builder()
                .name(NAME)
                .webSiteLink(WEBSITE_LINK)
                .build();
        provider.setId(1L);
        ProviderDto providerDto = providerMapper.toDto(provider);
        assertThat(providerDto.getName()).isEqualTo(provider.getName());
        assertThat(providerDto.getWebSiteLink()).isEqualTo(provider.getWebSiteLink());
        assertThat(providerDto.getId()).isEqualTo(provider.getId());
    }

    @Test
    @DisplayName("map to entity from dto default test case")
    @Disabled
    void mapToEntityTestCase() {
        ProviderDto providerDto = ProviderDto.builder()
                .id(20L)
                .name(NAME)
                .webSiteLink(WEBSITE_LINK)
                .build();
        Provider provider = providerMapper.toEntity(providerDto);
        assertThat(provider.getId()).isNull();
        assertThat(provider.getName()).isEqualTo(providerDto.getName());
        assertThat(provider.getWebSiteLink()).isEqualTo(providerDto.getWebSiteLink());
    }
}
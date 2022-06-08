package com.realo.estate.integration;

import com.realo.estate.domain.persistent.estate.Provider;
import com.realo.estate.service.ProviderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ProviderServiceIT extends TestcontainersTest {

    private static final String WE_CAPITAL = "WE Capital";
    private static final String A_100_DEVELOPMENT = "A100 Development";
    private static final String A_11_DEV = "A11 Dev";
    @Autowired
    private ProviderService providerService;

    @Test
    @DisplayName("save Provider default test case")
    void saveProviderTestCase() {
        Provider provider = Provider.builder()
                .name(WE_CAPITAL)
                .build();
        Provider save = providerService.save(provider);
        assertThat(save.getId()).isNotNull();
    }

    @Test
    @DisplayName("update provider default test case")
    void updateProviderTestCase() {
        Optional<Provider> maybeProvider = providerService.findByName(A_100_DEVELOPMENT);
        maybeProvider.ifPresent(provider -> {
            String nameToSet = A_11_DEV;
            if (!providerService.isProviderExistsByName(nameToSet)) {
                provider.setName(nameToSet);
                providerService.update(provider);
                assertThat(providerService.findByName(A_100_DEVELOPMENT)).isEmpty();
                assertThat(providerService.findByName(A_11_DEV)).isNotEmpty();
            }
        });
    }

    @Test
    @DisplayName("delete provider by id test case")
    void deleteProviderTestCase() {
        Optional<Provider> maybeProvider = providerService.findByName(A_100_DEVELOPMENT);
        maybeProvider.ifPresent(provider ->
                assertThat(providerService.deleteById(provider.getId())).isTrue());
    }
}
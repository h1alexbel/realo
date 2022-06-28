package com.realo.estate.repository;

import com.realo.estate.domain.persistence.estate.Provider;
import com.realo.estate.integration.TestcontainersTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ProviderRepositoryTest extends TestcontainersTest {

    @Autowired
    private ProviderRepository providerRepository;
    private static final String A100_WEBSITE_LINK = "https://a-100development.by/";
    private static final String A100_DEVELOPMENT = "A100 Development";

    @Test
    @DisplayName("Find by Provider name default test case")
    void findByNameTestCase() {
        Optional<Provider> maybeProvider = providerRepository
                .findByName(A100_DEVELOPMENT);
        assertThat(maybeProvider).isNotEmpty();
    }

    @Test
    @DisplayName("is exists by name default test case")
    void isExistsByNameTestCase() {
        boolean existsByName = providerRepository.existsByName(A100_DEVELOPMENT);
        assertThat(existsByName).isTrue();
    }

    @Test
    @DisplayName("is exists by web site link default test case")
    void isExistsByWebSiteLinkTestCase() {
        boolean existsByWebSiteLink = providerRepository.existsByWebSiteLink(A100_WEBSITE_LINK);
        assertThat(existsByWebSiteLink).isTrue();
    }
}
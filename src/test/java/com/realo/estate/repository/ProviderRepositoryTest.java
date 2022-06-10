package com.realo.estate.repository;

import com.realo.estate.domain.persistence.estate.Provider;
import com.realo.estate.integration.TestcontainersTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ProviderRepositoryTest extends TestcontainersTest {

    private static final String A = "A";
    private static final String A100_WEBSITE_LINK = "https://a-100development.by/";
    private static final String A100_DEVELOPMENT = "A100 Development";
    @Autowired
    private ProviderRepository providerRepository;

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

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Find by Provider name Null and Empty test case")
    void findByNameNullAndEmptyCase(String name) {
        assertDoesNotThrow(() -> providerRepository.findByName(name));
    }

    @Test
    @DisplayName("Find by Provider website link default test case")
    void findByWebSiteLinkTestCase() {
        Optional<Provider> maybeProvider = providerRepository
                .findByWebSiteLink(A100_WEBSITE_LINK);
        assertThat(maybeProvider).isNotEmpty();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Find by Provider web site link null and empty case")
    void findByWebSiteLinkNullAndEmptyCase(String webSiteLink) {
        assertDoesNotThrow(() -> providerRepository.findByWebSiteLink(webSiteLink));
    }

    @Test
    @DisplayName("Find all by Provider name containing test case")
    void findAllByNameContainingTestCase() {
        List<Provider> providers = providerRepository.findAllByNameContaining(A);
        assertThat(providers).hasSize(1);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Find all by Provider name containing null and empty test case")
    void findAllByNameContainingNullAndEmptyCase(String name) {
        assertDoesNotThrow(() -> providerRepository.findAllByNameContaining(name));
    }
}
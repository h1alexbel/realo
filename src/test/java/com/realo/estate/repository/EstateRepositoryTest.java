package com.realo.estate.repository;

import com.realo.estate.integration.TestcontainersTest;
import com.realo.estate.model.estate.Estate;
import com.realo.estate.model.estate.EstateType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class EstateRepositoryTest extends TestcontainersTest {

    private static final String MOSCOW = "Moscow";
    private static final String UKRAINE = "Ukraine";
    private static final String A100_DEVELOPMENT = "A100 Development";
    @Autowired
    private EstateRepository estateRepository;

    @Test
    @DisplayName("Update Estate type default test case")
    void updateEstateTypeTestCase() {
        Optional<Estate> maybeEstate = estateRepository.findById(1L);
        if (maybeEstate.isPresent()) {
            Estate estate = maybeEstate.get();
            estateRepository.updateEstateTypeById(EstateType.LIVING, estate.getId());
            Optional<Estate> maybeEstateAfter = estateRepository.findById(estate.getId());
            assertThat(maybeEstateAfter).isNotEmpty();
            assertThat(maybeEstateAfter.get().getEstateType()).isEqualTo(EstateType.LIVING);
        }
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Update Estate type null Id case")
    void updateEstateTypeNullIdCase(Long id) {
        assertDoesNotThrow(() -> estateRepository.updateEstateTypeById(EstateType.COMMERCIAL, id));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Update Estate type null EstateType case")
    void updateEstateTypeNullCase(EstateType estateType) {
        assertDoesNotThrow(() -> estateRepository.updateEstateTypeById(estateType, 1L));
    }

    @Test
    @DisplayName("Find all by Provider name default test case")
    void findAllByProviderNameTestCase() {
        List<Estate> estates = estateRepository.findAllByProviderName(A100_DEVELOPMENT);
        assertThat(estates).hasSize(3);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Find all by Provider name null and empty case")
    void findAllByProviderNameNullAndEmptyCase(String providerName) {
        assertDoesNotThrow(() -> estateRepository.findAllByProviderName(providerName));
    }

    @Test
    @DisplayName("Find all by EstateType default test case")
    void findAllByEstateTypeTestCase() {
        List<Estate> houses = estateRepository.findAllByEstateType(EstateType.LIVING);
        assertThat(houses).hasSize(2);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by EstateType null case")
    void findAllByEstateTypeNullCase(EstateType estateType) {
        assertDoesNotThrow(() -> estateRepository.findAllByEstateType(estateType));
    }

    @Test
    @DisplayName("Find all by Estate City default test case")
    void findAllByCityTestCase() {
        List<Estate> estates = estateRepository.findAllByAddressCity(MOSCOW);
        assertThat(estates).hasSize(4);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Find all by Estate City null and empty case")
    void findAllByCityNullAndEmptyCase(String city) {
        assertDoesNotThrow(() -> estateRepository.findAllByAddressCity(city));
    }

    @Test
    @DisplayName("Find all by Estate Country default test case")
    void findAllByCountryTestCase() {
        List<Estate> estates = estateRepository.findAllByAddressCountry(UKRAINE);
        assertThat(estates).hasSize(2);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Find all by Estate Country null and empty case")
    void findAllByCountryNullAndEmptyCase(String country) {
        assertDoesNotThrow(() -> estateRepository.findAllByAddressCountry(country));
    }

    @Test
    @DisplayName("Find all by Estate Year of construction default test case")
    void findAllByYearOfConstructionTestCase() {
        List<Estate> estates = estateRepository.findAllByYearOfConstruction(2001);
        assertThat(estates).hasSize(1);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by Estate Year of construction null case")
    void findAllByYearOfConstructionNullCase(Integer year) {
        assertDoesNotThrow(() -> estateRepository.findAllByYearOfConstruction(year));
    }

    @Test
    @DisplayName("Find all by Estate Square default test case")
    void findAllBySquareTestCase() {
        List<Estate> estates = estateRepository.findAllBySquare(45.2);
        assertThat(estates).hasSize(1);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by Estate Square Null case")
    void findAllBySquareNullCase(Double square) {
        assertDoesNotThrow(() -> estateRepository.findAllBySquare(square));
    }

    @Test
    @DisplayName("Find all by Estate Year of construction between two values default test case")
    void findAllByYearBetweenTestCase() {
        List<Estate> estates = estateRepository
                .findAllByYearOfConstructionBetween(2008, 2012);
        assertThat(estates).hasSize(2);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by Estate Year of construction between from null case")
    void findAllByYearBetweenYearFromNullCase(Integer from) {
        assertDoesNotThrow(() -> estateRepository
                .findAllByYearOfConstructionBetween(from, 2021));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by Estate Year of construction between to null case")
    void findAllByYearBetweenYearToNullCase(Integer to) {
        assertDoesNotThrow(() -> estateRepository
                .findAllByYearOfConstructionBetween(2018, to));
    }

    @Test
    @DisplayName("Find all by Estate Square between two values default test case")
    void findAllBySquareBetweenTestCase() {
        List<Estate> estates = estateRepository
                .findAllBySquareBetween(45.0, 62.5);
        assertThat(estates).hasSize(5);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by Estate Square between start null case")
    void findAllBySquareBetweenStartNullCase(Double start) {
        assertDoesNotThrow(() -> estateRepository
                .findAllBySquareBetween(start, 84.0));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by Estate Square between to null case")
    void findAllBySquareBetweenToNullCase(Double to) {
        assertDoesNotThrow(() -> estateRepository
                .findAllBySquareBetween(70.0, to));
    }
}
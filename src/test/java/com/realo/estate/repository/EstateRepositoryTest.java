package com.realo.estate.repository;

import com.realo.estate.domain.persistent.estate.Estate;
import com.realo.estate.domain.persistent.estate.EstateType;
import com.realo.estate.integration.TestcontainersTest;
import com.realo.estate.repository.filter.EstateFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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
        EstateFilter filter = EstateFilter.builder()
                .providerName("A100 Development")
                .build();
        List<Estate> estates = estateRepository.findByFilter(filter);
        assertThat(estates).hasSize(1);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by Provider name null and empty case")
    void findAllByFilterNullCase(EstateFilter estateFilter) {
        assertDoesNotThrow(() -> estateRepository.findByFilter(estateFilter));
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
        EstateFilter estateFilter = EstateFilter.builder()
                .city(MOSCOW)
                .build();
        List<Estate> estates = estateRepository.findByFilter(estateFilter);
        assertThat(estates).hasSize(4);
    }

    @Test
    @DisplayName("Find all by Estate Country default test case")
    void findAllByCountryTestCase() {
        EstateFilter estateFilter = EstateFilter.builder()
                .country(UKRAINE)
                .build();
        List<Estate> estates = estateRepository.findByFilter(estateFilter);
        assertThat(estates).hasSize(2);
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
        EstateFilter estateFilter = EstateFilter.builder()
                .yearFrom(2008)
                .yearTo(2012)
                .build();
        List<Estate> estates = estateRepository
                .findByFilter(estateFilter);
        assertThat(estates).hasSize(2);
    }

    @Test
    @DisplayName("Find all by Estate Square between two values default test case")
    void findAllBySquareBetweenTestCase() {
        EstateFilter estateFilter = EstateFilter.builder()
                .squareFrom(45.0)
                .squareTo(62.5)
                .build();
        List<Estate> estates = estateRepository
                .findByFilter(estateFilter);
        assertThat(estates).hasSize(5);
    }
}
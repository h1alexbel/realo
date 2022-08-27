package com.realo.estate.repository;

import com.realo.estate.domain.estate.Estate;
import com.realo.estate.domain.estate.EstateType;
import com.realo.estate.dto.EstateFilter;
import com.realo.estate.integration.TestcontainersTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class EstateRepositoryTest extends TestcontainersTest {

  private static final String MOSCOW = "Moscow";
  private static final String UKRAINE = "Ukraine";

  @Autowired
  private EstateRepository estateRepository;

  @Test
  @DisplayName("Find all by Provider name default test case")
  void findAllByProviderNameTestCase() {
    EstateFilter filter = EstateFilter.builder()
        .providerName("A100 Development")
        .build();
    List<Estate> estates = estateRepository.findByFilter(filter);
    assertThat(estates).isNotEmpty();
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
    assertThat(houses).hasSize(1);
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
  @DisplayName("Find all by Estate Year of construction between two values default test case")
  void findAllByYearBetweenTestCase() {
    EstateFilter estateFilter = EstateFilter.builder()
        .yearFrom(2008)
        .yearTo(2012)
        .build();
    List<Estate> estates = estateRepository
        .findByFilter(estateFilter);
    assertThat(estates).hasSize(3);
  }

  @Test
  @DisplayName("Find all by Estate Square between two values default test case")
  void findAllBySquareBetweenTestCase() {
    EstateFilter estateFilter = EstateFilter.builder()
        .squareFrom(20.0)
        .squareTo(120.00)
        .build();
    List<Estate> estates = estateRepository
        .findByFilter(estateFilter);
    assertThat(estates).hasSize(7);
  }

  @ParameterizedTest
  @NullSource
  @DisplayName("Find by match null case")
  void findByMatchNullCase(EstateFilter filter) {
    assertDoesNotThrow(() -> estateRepository.findByFilter(filter));
  }
}
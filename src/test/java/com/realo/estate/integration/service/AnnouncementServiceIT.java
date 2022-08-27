package com.realo.estate.integration.service;

import com.realo.estate.dto.AnnouncementDto;
import com.realo.estate.dto.AnnouncementFilter;
import com.realo.estate.exception.ResourceNotFoundException;
import com.realo.estate.integration.TestcontainersTest;
import com.realo.estate.service.AnnouncementService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnnouncementServiceIT extends TestcontainersTest {

  private static final String MUMBAI = "Mumbai";
  private static final String SO_CHEAP = "So cheap!";

  @Autowired
  private AnnouncementService announcementService;

  @Test
  @DisplayName("find by title default test case")
  void findByTitleTestCase() {
    AnnouncementDto byTitle = announcementService.findByTitle(SO_CHEAP);
    assertThat(byTitle).isNotNull();
  }

  @ParameterizedTest
  @NullSource
  @DisplayName("find by title null case")
  void findByTitleNullCase(String title) {
    assertThrows(ResourceNotFoundException.class,
        () -> announcementService.findByTitle(title));
  }

  @Test
  @DisplayName("find all by filter default test case")
  void findAllByFilter() {
    AnnouncementFilter filter = AnnouncementFilter.builder()
        .city(MUMBAI)
        .build();
    List<AnnouncementDto> allByFilter = announcementService.findAll(filter);
    assertThat(allByFilter).hasSize(1);
  }
}
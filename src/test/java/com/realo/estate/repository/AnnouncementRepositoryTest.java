package com.realo.estate.repository;

import com.realo.estate.domain.persistence.announcement.Announcement;
import com.realo.estate.domain.persistence.announcement.AnnouncementType;
import com.realo.estate.domain.persistence.announcement.CurrencyType;
import com.realo.estate.integration.TestcontainersTest;
import com.realo.estate.repository.filter.AnnouncementFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertSame;

class AnnouncementRepositoryTest extends TestcontainersTest {

    @Autowired
    private AnnouncementRepository announcementRepo;
    private static final String MINSK_WORLD = "Minsk World";
    private static final String MOSKOVSKAYA = "Moskovskaya";
    private static final String GO_DISTRICT = "Go Disc";
    private static final String TITLE = "Title";
    public static final String SO_CHEAP = "So cheap!";

    @Test
    @DisplayName("Update AnnouncementType by Announcement Id default test case")
    void updateAnnouncementTypeByIdTestCase() {
        Optional<Announcement> maybeAnnouncement = announcementRepo.findById(1L);
        if (maybeAnnouncement.isPresent()) {
            Announcement announcement = maybeAnnouncement.get();
            announcementRepo.updateAnnouncementTypeById(AnnouncementType.LONG_TERM_RENT,
                    announcement.getId());
            Optional<Announcement> maybeAnnouncementAfter = announcementRepo
                    .findById(announcement.getId());
            assertThat(maybeAnnouncementAfter).isNotEmpty();
            assertSame(AnnouncementType.LONG_TERM_RENT,
                    maybeAnnouncementAfter.get().getAnnouncementType());
        }
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Update AnnouncementType By Null Id case")
    void updateAnnouncementTypeByIdNullCase(Long id) {
        assertDoesNotThrow(() -> announcementRepo
                .updateAnnouncementTypeById(AnnouncementType.FOR_SELL, id));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("")
    void updateAnnouncementTypeNullCase(AnnouncementType announcementType) {
        assertDoesNotThrow(() -> announcementRepo
                .updateAnnouncementTypeById(announcementType, 1L));
    }

    @Test
    @DisplayName("Find by title default test case")
    void findByTitleTestCase() {
        Optional<Announcement> maybeAnnouncement = announcementRepo.findByTitle(SO_CHEAP);
        assertThat(maybeAnnouncement).isNotEmpty();
    }

    @Test
    @DisplayName("is exists by title default test case")
    void existsByTitleTestCase() {
        boolean existsByTitle = announcementRepo.existsByTitle(TITLE);
        assertThat(existsByTitle).isFalse();
    }

    @Test
    @DisplayName("Find all by title containing test case")
    void findAllByTitleContainingTestCase() {
        List<Announcement> announcements = announcementRepo
                .findAllByTitleContaining(MINSK_WORLD);
        assertThat(announcements).isEmpty();
    }

    @Test
    @DisplayName("Find all by AnnouncementType default test case")
    void findAllByAnnouncementTypeTestCase() {
        List<Announcement> announcements = announcementRepo
                .findAllByAnnouncementType(AnnouncementType.LONG_TERM_RENT);
        assertThat(announcements).hasSize(1);
    }

    @Test
    @DisplayName("Find all by Metro station default test case")
    void findAllByByMetroStationTestCase() {
        AnnouncementFilter announcementFilter = AnnouncementFilter.builder()
                .metroStation(MOSKOVSKAYA)
                .build();
        List<Announcement> announcements = announcementRepo
                .findByFilter(announcementFilter);
        assertThat(announcements).hasSize(1);
    }

    @Test
    @DisplayName("Find all by Estate district default test case")
    void findAllByDistrictTestCase() {
        AnnouncementFilter filter = AnnouncementFilter.builder()
                .district(GO_DISTRICT)
                .build();
        List<Announcement> announcements = announcementRepo.findByFilter(filter);
        assertThat(announcements).hasSize(2);
    }

    @Test
    @DisplayName("Find all by Estate Year of construction default test case")
    void findAllByYearOfConstruction() {
        AnnouncementFilter filter = AnnouncementFilter.builder()
                .yearOfConstruction(2019)
                .build();
        List<Announcement> announcements = announcementRepo.findByFilter(filter);
        assertThat(announcements).hasSize(1);
    }

    @Test
    @DisplayName("Find all by Estate Square between default test case")
    void findAllBySquareBetweenTestCase() {
        AnnouncementFilter filter = AnnouncementFilter.builder()
                .fromSquare(45.2)
                .toSquare(78.0)
                .build();
        List<Announcement> announcements = announcementRepo
                .findByFilter(filter);
        assertThat(announcements).hasSize(2);
    }

    @Test
    @DisplayName("Find all by CurrencyType default test case")
    void findAllByCurrencyTypeTestCase() {
        AnnouncementFilter filter = AnnouncementFilter.builder()
                .currencyType(CurrencyType.USD)
                .build();
        List<Announcement> announcements = announcementRepo
                .findByFilter(filter);
        assertThat(announcements).hasSize(4);
    }

    @Test
    @DisplayName("Find all Announcements with Loan option")
    void findAllWithLoanOptionTestCase() {
        AnnouncementFilter filter = AnnouncementFilter.builder()
                .isLoanable(true)
                .build();
        List<Announcement> allWithLoanOption = announcementRepo.findByFilter(filter);
        assertThat(allWithLoanOption).hasSize(5);
    }

    @Test
    @DisplayName("Find by match default test case")
    void findByMatchDefaultTestCase() {
        List<Announcement> byMatch = announcementRepo.findByFilter(
                AnnouncementFilter.builder()
                        .currencyType(CurrencyType.USD)
                        .fromPrice(new BigDecimal(1200))
                        .toPrice(new BigDecimal(67000))
                        .build()
        );
        assertThat(byMatch).isNotEmpty();
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find by match null case")
    void findByMatchNullCase(AnnouncementFilter filter) {
        assertDoesNotThrow(() -> announcementRepo.findByFilter(filter));
    }
}
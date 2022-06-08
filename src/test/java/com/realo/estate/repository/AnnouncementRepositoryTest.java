package com.realo.estate.repository;

import com.realo.estate.domain.persistent.announcement.Announcement;
import com.realo.estate.domain.persistent.announcement.AnnouncementType;
import com.realo.estate.domain.persistent.announcement.CurrencyType;
import com.realo.estate.integration.TestcontainersTest;
import com.realo.estate.repository.filter.AnnouncementFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AnnouncementRepositoryTest extends TestcontainersTest {

    private static final String MINSK_WORLD = "Minsk World";
    private static final String MOSKOVSKAYA = "Moskovskaya";
    private static final String CENTRAL_MAIN_DISTRICT = "Central-Main";
    private static final String ALEXEY_77_LOGIN = "alexey77";
    private static final String CATT_123_LOGIN = "catt123";

    private static final String TITLE = "Title";
    @Autowired
    private AnnouncementRepository announcementRepo;

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
            assertThat(maybeAnnouncementAfter.get().getAnnouncementType())
                    .isEqualTo(AnnouncementType.LONG_TERM_RENT);
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
        Optional<Announcement> maybeAnnouncement = announcementRepo.findByTitle("");
        assertThat(maybeAnnouncement).isNotEmpty();
    }

    @Test
    @DisplayName("is exists by title default test case")
    void existsByTitleTestCase() {
        boolean existsByTitle = announcementRepo.existsByTitle(TITLE);
        assertThat(existsByTitle).isFalse();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Find by title null and empty case")
    void findByTitleNullAndEmptyCase(String title) {
        assertDoesNotThrow(() -> announcementRepo.findByTitle(title));
    }

    @Test
    @DisplayName("Find all by title containing test case")
    void findAllByTitleContainingTestCase() {
        List<Announcement> announcements = announcementRepo
                .findAllByTitleContaining(MINSK_WORLD);
        assertThat(announcements).hasSize(3);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Find all by title containing null and empty case")
    void findAllByTitleContainingNullAndEmptyCase(String title) {
        assertDoesNotThrow(() -> announcementRepo.findAllByTitleContaining(title));
    }

    @Test
    @DisplayName("Find all by AnnouncementType default test case")
    void findAllByAnnouncementTypeTestCase() {
        List<Announcement> announcements = announcementRepo
                .findAllByAnnouncementType(AnnouncementType.SHORT_TERM_RENT);
        assertThat(announcements).hasSize(1);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by AnnouncementType Null case")
    void findAllByAnnouncementTypeNullCase(AnnouncementType announcementType) {
        assertDoesNotThrow(() -> announcementRepo.findAllByAnnouncementType(announcementType));
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
                .district(CENTRAL_MAIN_DISTRICT)
                .build();
        List<Announcement> announcements = announcementRepo.findByFilter(filter);
        assertThat(announcements).hasSize(2);
    }

    @Test
    @DisplayName("Find all by Estate Year of construction default test case")
    void findAllByYearOfConstruction() {
        AnnouncementFilter filter = AnnouncementFilter.builder()
                .yearOfConstruction(2018)
                .build();
        List<Announcement> announcements = announcementRepo.findByFilter(filter);
        assertThat(announcements).hasSize(4);
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
        assertThat(announcements).hasSize(6);
    }

    @Test
    @DisplayName("Find all by Announcement Author login default test case")
    void findAllByAuthorLoginTestCase() {
        AnnouncementFilter filter = AnnouncementFilter.builder()
                .authorLogin(ALEXEY_77_LOGIN)
                .build();
        List<Announcement> announcements = announcementRepo
                .findByFilter(filter);
        assertThat(announcements).hasSize(2);
    }

    @Test
    @DisplayName("Find all liked Announcements by User login default test case")
    void findAllLikedByUserTestCase() {
        List<Announcement> announcements = announcementRepo
                .findAllLikedByUserLogin(CATT_123_LOGIN);
        assertThat(announcements).hasSize(3);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Find all liked Announcements by User login null and empty test case")
    void findAllLikedByUserLoginNullAndEmptyCase(String login) {
        assertDoesNotThrow(() -> announcementRepo
                .findAllLikedByUserLogin(login));
    }

    @Test
    @DisplayName("Find all by created date between default test case")
    void findAllByCreatedDateBetweenTestCase() {
        LocalDate from = LocalDate.of(2021, Month.DECEMBER, 1);
        LocalDate to = LocalDate.of(2022, Month.APRIL, 1);
        List<Announcement> announcements = announcementRepo
                .findAllByCreatedAtBetween(from, to);
        assertThat(announcements).hasSize(5);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by created date between from null test case")
    void findAllByDateCreatedBetweenFromNullCase(LocalDate from) {
        LocalDate to = LocalDate.of(2022, Month.APRIL, 1);
        assertDoesNotThrow(() -> announcementRepo
                .findAllByCreatedAtBetween(from, to));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by created date between to null test case")
    void findAllByDateCreatedBetweenToNullCase(LocalDate to) {
        LocalDate from = LocalDate.of(2021, Month.DECEMBER, 1);
        assertDoesNotThrow(() -> announcementRepo
                .findAllByCreatedAtBetween(from, to));
    }

    @Test
    @DisplayName("Find all by Price and CurrencyType default test case")
    void findAllByPriceAndCurrencyTypeTestCase() {
        AnnouncementFilter filter = AnnouncementFilter.builder()
                .currencyType(CurrencyType.BYN)
                .toPrice(new BigDecimal(200_000))
                .build();
        List<Announcement> announcements = announcementRepo
                .findByFilter(filter);
        assertThat(announcements).hasSize(3);
    }

    @Test
    @DisplayName("Find all by Price between and CurrencyType default test case")
    void findAllByPriceBetweenAndCurrencyTypeTestCase() {
        BigDecimal from = new BigDecimal(45_000);
        BigDecimal to = new BigDecimal(55_000);
        AnnouncementFilter filter = AnnouncementFilter.builder()
                .fromPrice(from)
                .toPrice(to)
                .currencyType(CurrencyType.EUR)
                .build();
        List<Announcement> announcements = announcementRepo
                .findByFilter(filter);
        assertThat(announcements).hasSize(2);
    }

    @Test
    @DisplayName("Find all Announcements with Loan option")
    void findAllWithLoanOptionTestCase() {
        AnnouncementFilter filter = AnnouncementFilter.builder()
                .isLoanable(true)
                .build();
        List<Announcement> allWithLoanOption = announcementRepo.findByFilter(filter);
        assertThat(allWithLoanOption).hasSize(3);
    }

    @Test
    @DisplayName("Find by match default test case")
    void findByMatchDefaultTestCase() {
        List<Announcement> byMatch = announcementRepo.findByFilter(
                AnnouncementFilter.builder()
                        .yearOfConstruction(2012)
                        .currencyType(CurrencyType.USD)
                        .fromPrice(new BigDecimal(1200))
                        .toPrice(new BigDecimal(67000))
                        .build()
        );
        assertThat(byMatch).isNotEmpty();
    }

    @Test
    @DisplayName("Find by match another default test case")
    void findByMatchAnotherDefaultTestCase() {
        List<Announcement> byMatch = announcementRepo.findByFilter(
                AnnouncementFilter.builder()
                        .yearOfConstruction(2020)
                        .currencyType(CurrencyType.EUR)
                        .toPrice(new BigDecimal(58500))
                        .build()
        );
        assertThat(byMatch).isNotEmpty().hasSize(2);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find by match null case")
    void findByMatchNullCase(AnnouncementFilter filter) {
        assertDoesNotThrow(() -> announcementRepo.findByFilter(filter));
    }
}
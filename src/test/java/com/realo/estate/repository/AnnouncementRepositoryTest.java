package com.realo.estate.repository;

import com.realo.estate.integration.TestcontainersTest;
import com.realo.estate.domain.persistent.announcement.Announcement;
import com.realo.estate.domain.persistent.announcement.AnnouncementType;
import com.realo.estate.domain.persistent.announcement.CurrencyType;
import com.realo.estate.domain.persistent.estate.EstateType;
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
    @Autowired
    private AnnouncementRepository announcementRepo;

    @Test
    @DisplayName("Update AnnouncementType by Announcement Id default test case")
    void updateAnnouncementTypeByIdTestCase() {
        Optional<Announcement> maybeAnnouncement = announcementRepo.findById(1L);
        if (maybeAnnouncement.isPresent()) {
            Announcement announcement = maybeAnnouncement.get();
            announcementRepo.updateAnnouncementTypeById(AnnouncementType.LONG_TERN_RENT,
                    announcement.getId());
            Optional<Announcement> maybeAnnouncementAfter = announcementRepo
                    .findById(announcement.getId());
            assertThat(maybeAnnouncementAfter).isNotEmpty();
            assertThat(maybeAnnouncementAfter.get().getAnnouncementType())
                    .isEqualTo(AnnouncementType.LONG_TERN_RENT);
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
    @DisplayName("Find all by EstateType default test case")
    void findAllByEstateTypeTestCase() {
        List<Announcement> commercials = announcementRepo
                .findAllByEstateType(EstateType.COMMERCIAL);
        assertThat(commercials).hasSize(5);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by EstateType Null case")
    void findAllByEstateTypeNullCase(EstateType estateType) {
        assertDoesNotThrow(() -> announcementRepo.findAllByEstateType(estateType));
    }

    @Test
    @DisplayName("Find all by Metro station default test case")
    void findAllByByMetroStationTestCase() {
        List<Announcement> announcements = announcementRepo
                .findAllByClosestMetroStation(MOSKOVSKAYA);
        assertThat(announcements).hasSize(1);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Find all by Metro station null and empty case")
    void findAllByMetroStationNullAndEmptyCase(String station) {
        assertDoesNotThrow(() -> announcementRepo.findAllByClosestMetroStation(station));
    }

    @Test
    @DisplayName("Find all by Estate district default test case")
    void findAllByDistrictTestCase() {
        List<Announcement> announcements = announcementRepo.findAllByDistrict(CENTRAL_MAIN_DISTRICT);
        assertThat(announcements).hasSize(2);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Find all by Estate district null and empty test case")
    void findAllByDistrictNullAndEmptyCase(String district) {
        assertDoesNotThrow(() -> announcementRepo.findAllByDistrict(district));
    }

    @Test
    @DisplayName("Find all by Estate Year of construction default test case")
    void findAllByYearOfConstruction() {
        List<Announcement> announcements = announcementRepo.findAllByYearOfConstruction(2018);
        assertThat(announcements).hasSize(4);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by Estate Year of construction null test case")
    void findAllByYearOfConstructionNullAndEmptyCase(Integer year) {
        assertDoesNotThrow(() -> announcementRepo.findAllByYearOfConstruction(year));
    }

    @Test
    @DisplayName("Find all by Estate Year of construction between default test case")
    void findAllByYearOfConstructionBetweenTestCase() {
        List<Announcement> announcements = announcementRepo
                .findAllByYearOfConstructionBetween(2016, 2021);
        assertThat(announcements).hasSize(10);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by Estate Year of construction from null test case")
    void findAllByYearOfConstructionBetweenFromNullCase(Integer from) {
        assertDoesNotThrow(() -> announcementRepo
                .findAllByYearOfConstructionBetween(from, 2021));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by Estate Year of construction to null test case")
    void findAllByYearOfConstructionBetweenToNullCase(Integer to) {
        assertDoesNotThrow(() -> announcementRepo
                .findAllByYearOfConstructionBetween(2016, to));
    }

    @Test
    @DisplayName("Find all by Estate Square between default test case")
    void findAllBySquareBetweenTestCase() {
        List<Announcement> announcements = announcementRepo
                .findAllByEstateSquareBetween(45.2, 78.0);
        assertThat(announcements).hasSize(6);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by Estate Square from null test case")
    void findAllBySquareBetweenFromNullCase(Double from) {
        assertDoesNotThrow(() -> announcementRepo
                .findAllByEstateSquareBetween(from, 78.0));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by Estate Square to null test case")
    void findAllBySquareBetweenToNullCase(Double to) {
        assertDoesNotThrow(() -> announcementRepo
                .findAllByEstateSquareBetween(45.2, to));
    }

    @Test
    @DisplayName("Find all by Announcement Author login default test case")
    void findAllByAuthorLoginTestCase() {
        List<Announcement> announcements = announcementRepo
                .findAllByAnnouncementAuthorLogin(ALEXEY_77_LOGIN);
        assertThat(announcements).hasSize(2);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Find all by Announcement Author login null and empty test case")
    void findAllByAuthorLoginNullAndEmptyCase(String login) {
        assertDoesNotThrow(() -> announcementRepo
                .findAllByAnnouncementAuthorLogin(login));
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
        List<Announcement> announcements = announcementRepo
                .findAllByPriceAndCurrencyType(new BigDecimal(200_000), CurrencyType.BYN);
        assertThat(announcements).hasSize(3);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by Price and CurrencyType price null test case")
    void findAllByPriceNullAndCurrencyTypeCase(BigDecimal maxPrice) {
        assertDoesNotThrow(() -> announcementRepo
                .findAllByPriceAndCurrencyType(maxPrice, CurrencyType.EUR));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by Price and CurrencyType null test case")
    void findAllByPriceAndCurrencyTypeNullCase(CurrencyType currencyType) {
        assertDoesNotThrow(() -> announcementRepo
                .findAllByPriceAndCurrencyType(new BigDecimal(200_000), currencyType));
    }

    @Test
    @DisplayName("Find all by Price between and CurrencyType default test case")
    void findAllByPriceBetweenAndCurrencyTypeTestCase() {
        BigDecimal from = new BigDecimal(45_000);
        BigDecimal to = new BigDecimal(55_000);
        List<Announcement> announcements = announcementRepo
                .findAllByPriceBetweenAndCurrencyType(from, to, CurrencyType.EUR);
        assertThat(announcements).hasSize(2);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by Price between and CurrencyType from null case")
    void findAllByPriceBetweenFromNullAndCurrencyTypeCase(BigDecimal from) {
        BigDecimal to = new BigDecimal(55_000);
        assertDoesNotThrow(() -> announcementRepo
                .findAllByPriceBetweenAndCurrencyType
                        (from, to, CurrencyType.RUB));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by Price between and CurrencyType to null case")
    void findAllByPriceBetweenToNullAndCurrencyTypeCase(BigDecimal to) {
        BigDecimal from = new BigDecimal(45_000);
        assertDoesNotThrow(() -> announcementRepo
                .findAllByPriceBetweenAndCurrencyType
                        (from, to, CurrencyType.RUB));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by Price between and CurrencyType null case")
    void findAllByPriceBetweenAndCurrencyTypeNullCase(CurrencyType currencyType) {
        BigDecimal from = new BigDecimal(45_000);
        BigDecimal to = new BigDecimal(55_000);
        assertDoesNotThrow(() -> announcementRepo
                .findAllByPriceBetweenAndCurrencyType
                        (from, to, currencyType));
    }

    @Test
    @DisplayName("Find all Announcements with Loan option")
    void findAllWithLoanOptionTestCase() {
        List<Announcement> allWithLoanOption = announcementRepo.findAllWithLoanOption();
        assertThat(allWithLoanOption).hasSize(3);
    }

    @Test
    @DisplayName("Find by match default test case")
    void findByMatchDefaultTestCase() {
        List<Announcement> byMatch = announcementRepo.findByMatch(
                AnnouncementFilter.builder()
                        .yearOfConstruction(2012)
                        .currencyType(CurrencyType.USD)
                        .minPrice(new BigDecimal(1200))
                        .maxPrice(new BigDecimal(67000))
                        .build()
        );
        assertThat(byMatch).isNotEmpty();
    }

    @Test
    @DisplayName("Find by match another default test case")
    void findByMatchAnotherDefaultTestCase() {
        List<Announcement> byMatch = announcementRepo.findByMatch(
                AnnouncementFilter.builder()
                        .yearOfConstruction(2020)
                        .currencyType(CurrencyType.EUR)
                        .maxPrice(new BigDecimal(58500))
                        .build()
        );
        assertThat(byMatch).isNotEmpty().hasSize(2);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find by match null case")
    void findByMatchNullCase(AnnouncementFilter filter) {
        assertDoesNotThrow(() -> announcementRepo.findByMatch(filter));
    }
}
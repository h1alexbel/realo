package com.realo.estate.repository;

import com.realo.estate.domain.persistent.announcement.Announcement;
import com.realo.estate.domain.persistent.announcement.AnnouncementType;
import com.realo.estate.domain.persistent.announcement.CurrencyType;
import com.realo.estate.domain.persistent.estate.EstateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AnnouncementRepository
        extends JpaRepository<Announcement, Long>,
        FilterAnnouncementRepository,
        RevisionRepository<Announcement, Long, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "update Announcement a set a.announcementType =:announcementType" +
                   " where a.id =:id")
    void updateAnnouncementTypeById(AnnouncementType announcementType, Long id);

    Optional<Announcement> findByTitle(String title);

    List<Announcement> findAllByTitleContaining(String title);

    List<Announcement> findAllByAnnouncementType(AnnouncementType announcementType);

    @Query("select a from Announcement a join a.estate e where e.estateType =:estateType")
    List<Announcement> findAllByEstateType(EstateType estateType);

    @Query("select a from Announcement a join a.estate e" +
           " where e.address.closestMetroStation =:metroStation")
    List<Announcement> findAllByClosestMetroStation(String metroStation);

    @Query("select a from Announcement a join a.estate e where e.address.district =:district")
    List<Announcement> findAllByDistrict(String district);

    @Query("select a from Announcement a join a.estate e where e.yearOfConstruction =:year")
    List<Announcement> findAllByYearOfConstruction(Integer year);

    @Query("select a from Announcement a join a.estate e where e.yearOfConstruction" +
           " between :from and :to")
    List<Announcement> findAllByYearOfConstructionBetween(Integer from, Integer to);

    List<Announcement> findAllByEstateSquareBetween(Double from, Double to);

    List<Announcement> findAllByAnnouncementAuthorLogin(String login);

    @Query("select a from Announcement a join a.interestedUsers u where u.login =:login")
    List<Announcement> findAllLikedByUserLogin(String login);

    List<Announcement> findAllByCreatedAtBetween(LocalDate from, LocalDate to);

    @Query("select a from Announcement a where a.paymentInfo.price <=:maxPrice " +
           "and a.paymentInfo.currencyType =:type")
    List<Announcement> findAllByPriceAndCurrencyType(BigDecimal maxPrice, CurrencyType type);

    @Query("select a from Announcement a where a.paymentInfo.price between :from and :to" +
           " and a.paymentInfo.currencyType =:type")
    List<Announcement> findAllByPriceBetweenAndCurrencyType(BigDecimal from, BigDecimal to, CurrencyType type);

    @Query("select a from Announcement a where a.paymentInfo.isLoanPossible = true")
    List<Announcement> findAllWithLoanOption();
}
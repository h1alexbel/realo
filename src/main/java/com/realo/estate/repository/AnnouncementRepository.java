package com.realo.estate.repository;

import com.realo.estate.domain.announcement.Announcement;
import com.realo.estate.domain.announcement.AnnouncementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AnnouncementRepository extends
    JpaRepository<Announcement, Long>,
    FilterAnnouncementRepository,
    RevisionRepository<Announcement, Long, Long> {

  Optional<Announcement> findByTitle(String title);

  List<Announcement> findAllByTitleContaining(String title);

  List<Announcement> findAllByAnnouncementType(AnnouncementType announcementType);

  @Query("select a from Announcement a join a.interestedUsers u where u.login =:login")
  List<Announcement> findAllLikedByUserLogin(String login);

  List<Announcement> findAllByCreatedAtBetween(LocalDate from, LocalDate to);

  boolean existsByTitle(String title);
}
package com.realo.estate.repository;

import com.realo.estate.domain.persistence.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends
        JpaRepository<User, Long>,
        FilterUserRepository,
        RevisionRepository<User, Long, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO user_storage.user_interests(announcement_id, user_id) " +
                   "VALUES (:announcementId, :userId)",
            nativeQuery = true)
    void addToInterests(Long announcementId, Long userId);

    Optional<User> findByLogin(String login);

    List<User> findAllByLoginContaining(String login);

    Optional<User> findByEmail(String email);

    @Query("select u from User u join u.announcementInterests i where i.title =:title")
    List<User> findAllByLikedAnnouncement(String title);

    boolean existsByLogin(String login);

    boolean existsByEmail(String email);
}
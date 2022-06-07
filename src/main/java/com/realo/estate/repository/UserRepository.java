package com.realo.estate.repository;

import com.realo.estate.domain.persistent.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends
        JpaRepository<User, Long>,
        FilterUserRepository,
        RevisionRepository<User, Long, Long> {

    Optional<User> findByLogin(String login);

    List<User> findAllByLoginContaining(String login);

    Optional<User> findByEmail(String email);

    @Query("select u from User u join u.announcementInterests i where i.title =:title")
    List<User> findAllByLikedAnnouncement(String title);
}
package com.realo.estate.repository;

import com.realo.estate.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>,
        RevisionRepository<User, Long, Long> {

    Optional<User> findByLogin(String login);

    List<User> findAllByLoginContaining(String login);

    Optional<User> findByEmail(String email);

    List<User> findAllByFirstNameAndLastName(String firstName, String lastName);

    List<User> findAllByFirstName(String firstName);

    List<User> findAllByLastName(String lastName);

    @Query("select u from User u where u.userAddress.country =:country")
    List<User> findAllByCountry(String country);

    @Query("select u from User u join u.announcementInterests i where i.title =:title")
    List<User> findAllByLikedAnnouncement(String title);
}
package com.realo.estate.repository;

import com.realo.estate.integration.TestcontainersTest;
import com.realo.estate.domain.persistent.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class UserRepositoryTest extends TestcontainersTest {

    private static final String ANNOUNCEMENT_TITLE = "Сдам в аренду на длительный срок 1 комнатную квартиру в д. Копище, ул. Михайлашева, дом 5";
    private static final String BELARUS = "Belarus";
    private static final String IVANOV = "Ivanov";
    private static final String JOHN = "John";
    private static final String DOE = "Doe";
    private static final String O = "o";
    private static final String DOE_EMAIL = "doe@gmail.com";
    private static final String DOE_LOGIN = "doe12345";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnnouncementRepository announcementRepository;

    @Test
    @DisplayName("Find by User login default test case")
    void findByLoginTestCase() {
        Optional<User> maybeUser = userRepository.findByLogin(DOE_LOGIN);
        assertThat(maybeUser).isNotEmpty();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Find by User login null and empty test case")
    void findByLoginNullAndEmptyCase(String login) {
        assertDoesNotThrow(() -> userRepository.findByLogin(login));
    }

    @Test
    @DisplayName("Find by User email default test case")
    void findByEmailTestCase() {
        Optional<User> maybeUser = userRepository.findByEmail(DOE_EMAIL);
        assertThat(maybeUser).isNotEmpty();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Find by User email null and empty test case")
    void findByEmailNullAndEmptyCase(String email) {
        assertDoesNotThrow(() -> userRepository.findByEmail(email));
    }

    @Test
    @DisplayName("Find all by User login containing test case")
    void findAllByLoginContainingTestCase() {
        List<User> users = userRepository.findAllByLoginContaining(O);
        assertThat(users).hasSize(5);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Find all by User login containing null and empty test case")
    void findAllByLoginContainingNullAndEmptyCase(String login) {
        assertDoesNotThrow(() -> userRepository.findAllByLoginContaining(login));
    }

    @Test
    @DisplayName("Find all by Users firstname and lastname default test case")
    void findAllByFirstNameAndLastNameTestCase() {
        List<User> users = userRepository.findAllByFirstNameAndLastName(JOHN, DOE);
        assertThat(users).hasSize(2);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Find all by User null and empty firstName test case")
    void findAllByFirstNameNullAndEmptyCaseAndLastName(String firstName) {
        assertDoesNotThrow(() -> userRepository
                .findAllByFirstNameAndLastName(firstName, DOE));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Find all by User null and empty lastName test case")
    void findAllByLastNameNullAndEmptyCaseAndFirstName(String lastName) {
        assertDoesNotThrow(() -> userRepository
                .findAllByFirstNameAndLastName(JOHN, lastName));
    }

    @Test
    @DisplayName("Find all by User firstname default test case")
    void findAllByFirstNameTestCase() {
        List<User> johns = userRepository.findAllByFirstName(JOHN);
        assertThat(johns).hasSize(3);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Find all by User firstname null and empty case")
    void findAllByFirstNameNullAndEmptyCase(String firstName) {
        assertDoesNotThrow(() -> userRepository.findAllByFirstName(firstName));
    }

    @Test
    @DisplayName("Find all by User lastname default test case")
    void findAllByLastNameTestCase() {
        List<User> users = userRepository.findAllByLastName(IVANOV);
        assertThat(users).hasSize(5);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Find all by User lastname null and empty case")
    void findAllByLastNameNullAndEmptyCase(String lastName) {
        assertDoesNotThrow(() -> userRepository.findAllByLastName(lastName));
    }

    @Test
    @DisplayName("Find all by User country default test case")
    void findAllByCountryTestCase() {
        List<User> usersFromBelarus = userRepository.findAllByCountry(BELARUS);
        assertThat(usersFromBelarus).hasSize(7);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Find all by User country null and empty case")
    void findAllByCountryNullAndEmptyCase(String country) {
        assertDoesNotThrow(() -> userRepository.findAllByCountry(country));
    }

    @Test
    @DisplayName("Find all by liked Announcement title default test case")
    void findAllByLikedAnnouncementTitleTestCase() {
        List<User> usersThatLike = userRepository.findAllByLikedAnnouncement(ANNOUNCEMENT_TITLE);
        assertThat(usersThatLike).hasSize(2);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Find all by liked Announcement title null and empty case")
    void findAllByLikedAnnouncementTitleNullAndEmptyCase(String title) {
        assertDoesNotThrow(() -> userRepository.findAllByLikedAnnouncement(title));
    }
}
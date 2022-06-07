package com.realo.estate.repository;

import com.realo.estate.domain.persistent.user.User;
import com.realo.estate.integration.TestcontainersTest;
import com.realo.estate.repository.filter.UserFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
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
        UserFilter filter = UserFilter.builder()
                .firstName(JOHN)
                .lastName(DOE)
                .build();
        List<User> users = userRepository.findByFilter(filter);
        assertThat(users).hasSize(1);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find all by UserFilter null test case")
    void findAllByFilterNullCase(UserFilter userFilter) {
        assertDoesNotThrow(() -> userRepository
                .findByFilter(userFilter));
    }

    @Test
    @DisplayName("Find all by User firstname default test case")
    void findAllByFirstNameTestCase() {
        UserFilter userFilter = UserFilter.builder()
                .firstName(JOHN).build();
        List<User> johns = userRepository.findByFilter(userFilter);
        assertThat(johns).hasSize(3);
    }

    @Test
    @DisplayName("Find all by User lastname default test case")
    void findAllByLastNameTestCase() {
        UserFilter userFilter = UserFilter.builder()
                .lastName(IVANOV)
                .build();
        List<User> users = userRepository.findByFilter(userFilter);
        assertThat(users).hasSize(5);
    }

    @Test
    @DisplayName("Find all by User country default test case")
    void findAllByCountryTestCase() {
        UserFilter userFilter = UserFilter.builder()
                .country(BELARUS)
                .build();
        List<User> usersFromBelarus = userRepository.findByFilter(userFilter);
        assertThat(usersFromBelarus).hasSize(7);
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
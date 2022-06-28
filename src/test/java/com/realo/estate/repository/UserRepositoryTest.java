package com.realo.estate.repository;

import com.realo.estate.domain.persistence.user.User;
import com.realo.estate.integration.TestcontainersTest;
import com.realo.estate.repository.filter.UserFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class UserRepositoryTest extends TestcontainersTest {

    @Autowired
    private UserRepository userRepository;
    private static final String BELARUS = "Belarus";
    private static final String IVANOV = "Ivanov";
    private static final String JOHN = "John";
    private static final String DOE = "Doe";
    private static final String O = "o";
    private static final String DOE_EMAIL = "doe@gmail.com";
    private static final String DOE_LOGIN = "jog123";
    private static final String CAT_LOGIN = "cat@cact";
    private static final String CAT_GMAIL = "cat@gmail.com";

    @Test
    @DisplayName("Find by User login default test case")
    void findByLoginTestCase() {
        Optional<User> maybeUser = userRepository.findByLogin(DOE_LOGIN);
        assertThat(maybeUser).isNotEmpty();
    }

    @Test
    @DisplayName("Find by User email default test case")
    void findByEmailTestCase() {
        Optional<User> maybeUser = userRepository.findByEmail(DOE_EMAIL);
        assertThat(maybeUser).isNotEmpty();
    }

    @Test
    @DisplayName("is exists by email default test case")
    void existsByEmailTestCase() {
        boolean existsByEmail = userRepository.existsByEmail(DOE_EMAIL);
        assertThat(existsByEmail).isTrue();
    }

    @Test
    @DisplayName("is exists by email that not represented in db test case")
    void existsByEmailFalseCase() {
        boolean existsByEmail = userRepository.existsByEmail(CAT_GMAIL);
        assertThat(existsByEmail).isFalse();
    }

    @Test
    @DisplayName("is exists by login default test case")
    void existsByLoginTestCase() {
        boolean existsByLogin = userRepository.existsByLogin(DOE_LOGIN);
        assertThat(existsByLogin).isTrue();
    }

    @Test
    @DisplayName("is exists by login that not represented in db test case")
    void existsByLoginFalseCase() {
        boolean existsByLogin = userRepository.existsByLogin(CAT_LOGIN);
        assertThat(existsByLogin).isFalse();
    }

    @Test
    @DisplayName("Find all by User login containing test case")
    void findAllByLoginContainingTestCase() {
        List<User> users = userRepository.findAllByLoginContaining(O);
        assertThat(users).hasSize(1);
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

    @Test
    @DisplayName("Find all by User firstname default test case")
    void findAllByFirstNameTestCase() {
        UserFilter userFilter = UserFilter.builder()
                .firstName(JOHN).build();
        List<User> johns = userRepository.findByFilter(userFilter);
        assertThat(johns).hasSize(1);
    }

    @Test
    @DisplayName("Find all by User lastname default test case")
    void findAllByLastNameTestCase() {
        UserFilter userFilter = UserFilter.builder()
                .lastName(IVANOV)
                .build();
        List<User> users = userRepository.findByFilter(userFilter);
        assertThat(users).isEmpty();
    }

    @Test
    @DisplayName("Find all by User country default test case")
    void findAllByCountryTestCase() {
        UserFilter userFilter = UserFilter.builder()
                .country(BELARUS)
                .build();
        List<User> usersFromBelarus = userRepository.findByFilter(userFilter);
        assertThat(usersFromBelarus).hasSize(1);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Find by match null case")
    void findByMatchNullCase(UserFilter filter) {
        assertDoesNotThrow(() -> userRepository.findByFilter(filter));
    }
}
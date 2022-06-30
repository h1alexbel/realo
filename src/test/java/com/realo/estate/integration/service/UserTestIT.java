package com.realo.estate.integration.service;

import com.realo.estate.domain.dto.AnnouncementDto;
import com.realo.estate.domain.dto.UserDto;
import com.realo.estate.exception.ResourceNotFoundException;
import com.realo.estate.integration.TestcontainersTest;
import com.realo.estate.repository.filter.UserFilter;
import com.realo.estate.service.AnnouncementService;
import com.realo.estate.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTestIT extends TestcontainersTest {

    @Autowired
    private UserService userService;
    @Autowired
    private AnnouncementService announcementService;
    private static final String ALEX = "Alexey";
    private static final String PHONE_NUMBER = "+352352753";
    private static final String ALEXEY_77 = "Alexey77";

    @Test
    @DisplayName("find by login default test case")
    void findByLoginTestCase() {
        UserDto byLogin = userService.findByLogin(ALEXEY_77);
        assertThat(byLogin).isNotNull();
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("find by login null test case")
    void findByLoginNullCase(String login) {
        assertThrows(ResourceNotFoundException.class,
                () -> userService.findByLogin(login));
    }

    @Test
    @DisplayName("find all by filter default test case")
    void findAllByFilter() {
        UserFilter userFilter = UserFilter.builder()
                .phoneNumber(PHONE_NUMBER)
                .firstName(ALEX)
                .build();
        List<UserDto> allByFilter = userService.findAll(userFilter);
        assertThat(allByFilter).hasSize(1);
    }

    @Test
    @DisplayName("add announcement to user interests")
    void addToInterestsTestCase() {
        UserDto userDto = userService.findByLogin(ALEXEY_77);
        AnnouncementDto byTitle = announcementService.findByTitle("So cheap!");
        userService.addAnnouncementToInterests(userDto.getId(), byTitle.getId());
        List<UserDto> allByLikedAnnouncement = userService.findAllByLikedAnnouncement(byTitle.getTitle());
        List<AnnouncementDto> allLikedByUserLogin = announcementService.findAllLikedByUserLogin(ALEXEY_77);
        assertThat(allByLikedAnnouncement).hasSize(1);
        assertThat(allLikedByUserLogin).hasSize(1);
    }
}
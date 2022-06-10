package com.realo.estate.service;

import com.realo.estate.domain.dto.AnnouncementDto;
import com.realo.estate.domain.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService extends GenericCrudService<UserDto, Long> {

    void addAnnouncementToInterest(UserDto user, AnnouncementDto announcement);

    Optional<UserDto> findByLogin(String login);

    List<UserDto> findAllByLoginContaining(String login);

    Optional<UserDto> findByEmail(String email);

    List<UserDto> findAllByLikedAnnouncement(String title);

    boolean isExistsByLogin(String login);

    boolean isExistsByEmail(String email);
}
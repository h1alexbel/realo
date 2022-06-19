package com.realo.estate.service;

import com.realo.estate.domain.dto.AnnouncementDto;
import com.realo.estate.domain.dto.UserDto;

import java.util.List;

public interface UserService extends GenericCrudService<UserDto, Long> {

    void addAnnouncementToInterest(UserDto user, AnnouncementDto announcement);

    UserDto findByLogin(String login);

    List<UserDto> findAllByLoginContaining(String login);

    UserDto findByEmail(String email);

    List<UserDto> findAllByLikedAnnouncement(String title);
}
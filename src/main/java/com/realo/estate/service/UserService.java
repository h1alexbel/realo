package com.realo.estate.service;

import com.realo.estate.domain.dto.UserDto;
import com.realo.estate.repository.filter.UserFilter;

import java.util.List;

public interface UserService extends GenericCrudService<UserDto, Long> {

    void addAnnouncementToInterests(Long userId, Long announcementId);

    List<UserDto> findAll(UserFilter filter);

    UserDto findByLogin(String login);

    List<UserDto> findAllByLoginContaining(String login);

    UserDto findByEmail(String email);

    List<UserDto> findAllByLikedAnnouncement(String title);
}
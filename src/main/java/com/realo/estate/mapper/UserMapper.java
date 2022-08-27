package com.realo.estate.mapper;

import com.realo.estate.dto.UserDto;
import com.realo.estate.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "announcements", ignore = true)
  @Mapping(target = "announcementInterests", ignore = true)
  User toEntity(UserDto userDto);

  UserDto toDto(User user);
}
package com.realo.estate.domain.mapper;

import com.realo.estate.domain.dto.UserDto;
import com.realo.estate.domain.persistence.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "announcements", ignore = true)
    @Mapping(target = "announcementInterests", ignore = true)
    User toEntity(UserDto userDto);

    UserDto toDto(User user);
}
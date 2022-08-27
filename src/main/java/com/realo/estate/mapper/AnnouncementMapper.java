package com.realo.estate.mapper;

import com.realo.estate.dto.AnnouncementDto;
import com.realo.estate.domain.announcement.Announcement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {

  Announcement toEntity(AnnouncementDto announcementDto);

  AnnouncementDto toDto(Announcement announcement);
}
package com.realo.estate.domain.mapper;

import com.realo.estate.domain.dto.AnnouncementDto;
import com.realo.estate.domain.persistence.announcement.Announcement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {

    Announcement toEntity(AnnouncementDto announcementDto);

    AnnouncementDto toDto(Announcement announcement);
}
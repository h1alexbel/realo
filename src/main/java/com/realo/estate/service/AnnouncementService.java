package com.realo.estate.service;

import com.realo.estate.domain.dto.AnnouncementDto;
import com.realo.estate.domain.persistence.announcement.AnnouncementType;
import com.realo.estate.repository.filter.AnnouncementFilter;

import java.util.List;

public interface AnnouncementService extends GenericCrudService<AnnouncementDto, Long> {

    void updateAnnouncementTypeById(AnnouncementType announcementType, Long id);

    AnnouncementDto findByTitle(String title);

    List<AnnouncementDto> findAll(AnnouncementFilter filter);

    List<AnnouncementDto> findAllByTitleContaining(String title);

    List<AnnouncementDto> findAllByAnnouncementType(AnnouncementType announcementType);

    List<AnnouncementDto> findAllLikedByUserLogin(String login);
}
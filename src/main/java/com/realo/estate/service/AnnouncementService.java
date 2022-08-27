package com.realo.estate.service;

import com.realo.estate.dto.AnnouncementDto;
import com.realo.estate.dto.AnnouncementFilter;

import java.util.List;

public interface AnnouncementService extends GenericCrudService<AnnouncementDto, Long> {

  AnnouncementDto findByTitle(String title);

  List<AnnouncementDto> findAll(AnnouncementFilter filter);

  List<AnnouncementDto> findAllByTitleContaining(String title);

  List<AnnouncementDto> findAllLikedByUserLogin(String login);
}
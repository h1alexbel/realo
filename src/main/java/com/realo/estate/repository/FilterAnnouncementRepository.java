package com.realo.estate.repository;

import com.realo.estate.domain.persistent.announcement.Announcement;
import com.realo.estate.repository.filter.AnnouncementFilter;

import java.util.List;

public interface FilterAnnouncementRepository {

    List<Announcement> findByMatch(AnnouncementFilter announcementFilter);
}
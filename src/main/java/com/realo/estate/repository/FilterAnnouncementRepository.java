package com.realo.estate.repository;

import com.realo.estate.domain.announcement.Announcement;
import com.realo.estate.dto.AnnouncementFilter;

public interface FilterAnnouncementRepository
    extends FilterRepository<Announcement, AnnouncementFilter> {
}
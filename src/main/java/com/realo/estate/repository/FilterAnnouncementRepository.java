package com.realo.estate.repository;

import com.realo.estate.domain.persistent.announcement.Announcement;
import com.realo.estate.repository.filter.AnnouncementFilter;
import com.realo.estate.repository.filter.FilterRepository;

public interface FilterAnnouncementRepository
        extends FilterRepository<Announcement, AnnouncementFilter> {
}
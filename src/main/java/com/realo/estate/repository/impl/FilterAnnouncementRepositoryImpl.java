package com.realo.estate.repository.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.realo.estate.domain.persistent.announcement.Announcement;
import com.realo.estate.querydsl.QPredicates;
import com.realo.estate.repository.FilterAnnouncementRepository;
import com.realo.estate.repository.filter.AnnouncementFilter;

import javax.persistence.EntityManager;
import java.util.List;

import static com.realo.estate.domain.persistent.announcement.QAnnouncement.announcement;

public class FilterAnnouncementRepositoryImpl implements FilterAnnouncementRepository {

    private final EntityManager entityManager;

    public FilterAnnouncementRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Announcement> findByMatch(AnnouncementFilter filter) {
        Predicate predicate = QPredicates.builder()
                .add(filter.getAnnouncementType(), announcement.announcementType::eq)
                .add(filter.getCurrencyType(), announcement.paymentInfo.currencyType::eq)
                .add(filter.getMinPrice(), announcement.paymentInfo.price::gt)
                .add(filter.getMaxPrice(), announcement.paymentInfo.price::lt)
                .add(filter.getYearOfConstruction(),
                        announcement.estate.yearOfConstruction::eq)
                .add(filter.getMinSquare(), announcement.estate.square::gt)
                .add(filter.getMaxSquare(), announcement.estate.square::lt)
                .build();
        return new JPAQuery<Announcement>(entityManager)
                .select(announcement)
                .from(announcement)
                .where(predicate)
                .fetch();
    }
}
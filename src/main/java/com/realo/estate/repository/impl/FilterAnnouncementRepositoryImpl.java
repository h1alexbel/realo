package com.realo.estate.repository.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.realo.estate.domain.persistence.announcement.Announcement;
import com.realo.estate.repository.FilterAnnouncementRepository;
import com.realo.estate.repository.filter.AnnouncementFilter;
import com.realo.estate.repository.filter.querydsl.QPredicates;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static com.realo.estate.domain.persistence.announcement.QAnnouncement.announcement;

@RequiredArgsConstructor
public class FilterAnnouncementRepositoryImpl implements FilterAnnouncementRepository {

    private final EntityManager entityManager;

    @Override
    public List<Announcement> findByFilter(AnnouncementFilter filter) {
        if (filter != null) {
            Predicate predicate = QPredicates.builder()
                    .add(filter.getAnnouncementType(), announcement.announcementType::eq)
                    .add(filter.getCountry(), announcement.estate.address.country::eq)
                    .add(filter.getCity(), announcement.estate.address.city::eq)
                    .add(filter.getDistrict(), announcement.estate.address.district::eq)
                    .add(filter.getMetroStation(), announcement.estate.address.closestMetroStation::eq)
                    .add(filter.getCurrencyType(), announcement.paymentInfo.currencyType::eq)
                    .add(filter.getFromPrice(), announcement.paymentInfo.price::goe)
                    .add(filter.getToPrice(), announcement.paymentInfo.price::loe)
                    .add(filter.getYearOfConstruction(),
                            announcement.estate.yearOfConstruction::eq)
                    .add(filter.getFromSquare(), announcement.estate.square::goe)
                    .add(filter.getToSquare(), announcement.estate.square::loe)
                    .add(filter.getIsLoanable(), announcement.paymentInfo.isLoanPossible::eq)
                    .add(filter.getAuthorLogin(), announcement.announcementAuthor.login::eq)
                    .build();
            return new JPAQuery<Announcement>(entityManager)
                    .select(announcement)
                    .from(announcement)
                    .where(predicate)
                    .fetch();
        }
        return new JPAQuery<Announcement>(entityManager)
                .select(announcement)
                .from(announcement)
                .fetch();
    }
}
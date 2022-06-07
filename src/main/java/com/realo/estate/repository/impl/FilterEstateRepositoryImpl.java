package com.realo.estate.repository.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.realo.estate.domain.persistent.estate.Estate;
import com.realo.estate.repository.FilterEstateRepository;
import com.realo.estate.repository.filter.EstateFilter;
import com.realo.estate.repository.filter.querydsl.QPredicates;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static com.realo.estate.domain.persistent.estate.QEstate.estate;

@RequiredArgsConstructor
public class FilterEstateRepositoryImpl implements FilterEstateRepository {

    private final EntityManager entityManager;

    @Override
    public List<Estate> findByFilter(EstateFilter filter) {
        Predicate predicate = QPredicates.builder()
                .add(filter.getProviderName(), estate.provider.name::eq)
                .add(filter.getCountry(), estate.address.country::eq)
                .add(filter.getCity(), estate.address.city::eq)
                .add(filter.getDistrict(), estate.address.district::eq)
                .add(filter.getStreet(), estate.address.street::eq)
                .add(filter.getMetroStation(), estate.address.closestMetroStation::eq)
                .add(filter.getYearFrom(), estate.yearOfConstruction::goe)
                .add(filter.getYearTo(), estate.yearOfConstruction::loe)
                .add(filter.getSquareFrom(), estate.square::goe)
                .add(filter.getSquareTo(), estate.square::loe)
                .build();
        return new JPAQuery<Estate>(entityManager)
                .select(estate)
                .from(estate)
                .where(predicate)
                .fetch();
    }
}
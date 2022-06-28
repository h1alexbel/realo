package com.realo.estate.repository.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.realo.estate.domain.persistence.user.User;
import com.realo.estate.repository.FilterUserRepository;
import com.realo.estate.repository.filter.UserFilter;
import com.realo.estate.repository.filter.querydsl.QPredicates;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static com.realo.estate.domain.persistence.user.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

    @Override
    public List<User> findByFilter(UserFilter filter) {
        if (filter != null) {
            Predicate predicate = QPredicates.builder()
                    .add(filter.getFirstName(), user.firstName::eq)
                    .add(filter.getLastName(), user.lastName::eq)
                    .add(filter.getCountry(), user.userAddress.country::eq)
                    .add(filter.getCity(), user.userAddress.city::eq)
                    .add(filter.getPhoneNumber(), user.contactInfo.phoneNumber::eq)
                    .add(filter.getMessengerType(), user.contactInfo.preferredMessenger::eq)
                    .build();
            return new JPAQuery<User>(entityManager)
                    .select(user)
                    .from(user)
                    .where(predicate)
                    .fetch();
        }
        return new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .fetch();
    }
}
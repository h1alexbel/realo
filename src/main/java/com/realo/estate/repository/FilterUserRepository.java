package com.realo.estate.repository;

import com.realo.estate.domain.persistence.user.User;
import com.realo.estate.repository.filter.FilterRepository;
import com.realo.estate.repository.filter.UserFilter;

public interface FilterUserRepository extends FilterRepository<User, UserFilter> {
}
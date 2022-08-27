package com.realo.estate.repository;

import com.realo.estate.domain.user.User;
import com.realo.estate.dto.UserFilter;

public interface FilterUserRepository extends FilterRepository<User, UserFilter> {
}
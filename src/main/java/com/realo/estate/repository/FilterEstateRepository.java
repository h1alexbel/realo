package com.realo.estate.repository;

import com.realo.estate.domain.persistence.estate.Estate;
import com.realo.estate.repository.filter.EstateFilter;
import com.realo.estate.repository.filter.FilterRepository;

public interface FilterEstateRepository extends FilterRepository<Estate, EstateFilter> {
}
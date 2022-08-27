package com.realo.estate.repository;

import com.realo.estate.domain.estate.Estate;
import com.realo.estate.domain.estate.EstateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.List;

public interface EstateRepository extends
    JpaRepository<Estate, Long>,
    FilterEstateRepository,
    RevisionRepository<Estate, Long, Long> {

  List<Estate> findAllByEstateType(EstateType estateType);
}
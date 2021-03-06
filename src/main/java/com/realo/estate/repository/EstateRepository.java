package com.realo.estate.repository;

import com.realo.estate.domain.persistence.estate.Estate;
import com.realo.estate.domain.persistence.estate.EstateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.List;

public interface EstateRepository extends
        JpaRepository<Estate, Long>,
        FilterEstateRepository,
        RevisionRepository<Estate, Long, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Estate e set e.estateType = :estateType where e.id =:id")
    void updateEstateTypeById(EstateType estateType, Long id);

    List<Estate> findAllByEstateType(EstateType estateType);
}
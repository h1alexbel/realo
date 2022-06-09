package com.realo.estate.repository;

import com.realo.estate.domain.persistence.estate.Estate;
import com.realo.estate.domain.persistence.estate.EstateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstateRepository extends
        JpaRepository<Estate, Long>,
        FilterEstateRepository,
        RevisionRepository<Estate, Long, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Estate e set e.estateType = :estateType where e.id =:id")
    void updateEstateTypeById(@Param("estateType") EstateType estateTypeToSet, Long id);

    List<Estate> findAllByEstateType(EstateType estateType);

    List<Estate> findAllByYearOfConstruction(Integer year);

    List<Estate> findAllBySquare(Double square);
}
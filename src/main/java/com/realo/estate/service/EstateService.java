package com.realo.estate.service;

import com.realo.estate.domain.dto.EstateDto;
import com.realo.estate.domain.persistence.estate.EstateType;
import com.realo.estate.repository.filter.EstateFilter;

import java.util.List;

public interface EstateService extends GenericCrudService<EstateDto, Long> {

    void updateEstateTypeById(EstateType estateTypeToSet, Long id);

    List<EstateDto> findAll(EstateFilter filter);

    List<EstateDto> findAllByEstateType(EstateType estateType);

    List<EstateDto> findAllByYearOfConstruction(Integer year);

    List<EstateDto> findAllBySquare(Double square);
}
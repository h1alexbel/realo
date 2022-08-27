package com.realo.estate.service;

import com.realo.estate.domain.estate.EstateType;
import com.realo.estate.dto.EstateDto;
import com.realo.estate.dto.EstateFilter;

import java.util.List;

public interface EstateService extends GenericCrudService<EstateDto, Long> {

  List<EstateDto> findAll(EstateFilter filter);

  List<EstateDto> findAllByEstateType(EstateType estateType);
}
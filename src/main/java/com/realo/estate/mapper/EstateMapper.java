package com.realo.estate.mapper;

import com.realo.estate.dto.EstateDto;
import com.realo.estate.domain.estate.Estate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EstateMapper {

  @Mapping(target = "announcements", ignore = true)
  Estate toEntity(EstateDto estateDto);

  EstateDto toDto(Estate estate);
}
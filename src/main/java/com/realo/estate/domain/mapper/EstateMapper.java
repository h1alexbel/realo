package com.realo.estate.domain.mapper;

import com.realo.estate.domain.dto.EstateDto;
import com.realo.estate.domain.persistence.estate.Estate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EstateMapper {

    @Mapping(target = "announcements", ignore = true)
    Estate toEntity(EstateDto estateDto);

    EstateDto toDto(Estate estate);
}
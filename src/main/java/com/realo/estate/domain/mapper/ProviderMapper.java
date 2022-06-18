package com.realo.estate.domain.mapper;

import com.realo.estate.domain.dto.ProviderDto;
import com.realo.estate.domain.persistence.estate.Provider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProviderMapper {

    @Mapping(target = "projects", ignore = true)
    Provider toEntity(ProviderDto dto);

    ProviderDto toDto(Provider entity);
}
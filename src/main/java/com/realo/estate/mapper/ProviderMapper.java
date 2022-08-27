package com.realo.estate.mapper;

import com.realo.estate.dto.ProviderDto;
import com.realo.estate.domain.estate.Provider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProviderMapper {

  @Mapping(target = "projects", ignore = true)
  Provider toEntity(ProviderDto dto);

  ProviderDto toDto(Provider entity);
}
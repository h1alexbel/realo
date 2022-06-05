package com.realo.estate.mapper;

import com.realo.estate.dto.ProviderDto;
import com.realo.estate.model.estate.Provider;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProviderMapper {

    Provider toEntity(ProviderDto providerDto);

    ProviderDto toDto(Provider provider);
}
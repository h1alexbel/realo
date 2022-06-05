package com.realo.estate.service;

import com.realo.estate.dto.ProviderDto;

import java.util.List;
import java.util.Optional;

public interface ProviderService extends GenericCrudService<ProviderDto, Long> {

    Optional<ProviderDto> findByName(String name);

    Optional<ProviderDto> findByWebSiteLink(String webSiteLink);

    List<ProviderDto> findAllByNameContaining(String name);
}
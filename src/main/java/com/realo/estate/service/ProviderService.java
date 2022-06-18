package com.realo.estate.service;

import com.realo.estate.domain.dto.ProviderDto;

public interface ProviderService extends GenericCrudService<ProviderDto, Long> {

    ProviderDto findByName(String name);

    ProviderDto findByWebSiteLink(String webSiteLink);
}
package com.realo.estate.service;

import com.realo.estate.dto.ProviderDto;

public interface ProviderService extends GenericCrudService<ProviderDto, Long> {

  ProviderDto findByName(String name);
}
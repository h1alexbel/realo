package com.realo.estate.service;

import com.realo.estate.domain.persistence.estate.Provider;

import java.util.List;
import java.util.Optional;

public interface ProviderService extends GenericCrudService<Provider, Long> {

    Optional<Provider> findByName(String name);

    Optional<Provider> findByWebSiteLink(String webSiteLink);

    List<Provider> findAllByNameContaining(String name);

    boolean isProviderExistsByName(String name);

    boolean isProviderExistsByWebSiteLink(String webSiteLink);
}
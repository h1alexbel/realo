package com.realo.estate.repository;

import com.realo.estate.domain.persistence.estate.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.List;
import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Long>,
        RevisionRepository<Provider, Long, Long> {

    Optional<Provider> findByName(String providerName);

    Optional<Provider> findByWebSiteLink(String providerWebSiteLink);

    List<Provider> findAllByNameContaining(String name);

    boolean existsByName(String providerName);

    boolean existsByWebSiteLink(String webSiteLink);
}
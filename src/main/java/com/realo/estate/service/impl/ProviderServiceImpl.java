package com.realo.estate.service.impl;

import com.realo.estate.annotation.TransactionalRealoService;
import com.realo.estate.domain.persistence.estate.Provider;
import com.realo.estate.repository.ProviderRepository;
import com.realo.estate.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@TransactionalRealoService
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;

    @Transactional
    @Override
    public Provider save(Provider provider) {
        return Optional.of(provider)
                .map(providerRepository::save)
                .orElseThrow();
    }

    @Transactional
    @Override
    public void update(Provider provider) {
        Objects.requireNonNull(provider);
        providerRepository.saveAndFlush(provider);
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        Objects.requireNonNull(id);

        return providerRepository.findById(id)
                .map(provider -> {
                    providerRepository.delete(provider);
                    providerRepository.flush();
                    return true;
                })
                .orElse((false));
    }

    @Override
    public Optional<Provider> findById(Long id) {
        Objects.requireNonNull(id);
        return providerRepository.findById(id);
    }

    @Override
    public List<Provider> findAll() {
        return providerRepository.findAll();
    }

    @Override
    public Optional<Provider> findByName(String name) {
        Objects.requireNonNull(name);
        return providerRepository.findByName(name);
    }

    @Override
    public Optional<Provider> findByWebSiteLink(String webSiteLink) {
        Objects.requireNonNull(webSiteLink);
        return providerRepository.findByWebSiteLink(webSiteLink);
    }

    @Override
    public List<Provider> findAllByNameContaining(String name) {
        Objects.requireNonNull(name);
        return providerRepository.findAllByNameContaining(name);
    }

    @Override
    public boolean isProviderExistsByName(String name) {
        Objects.requireNonNull(name);
        return providerRepository.existsByName(name);
    }

    @Override
    public boolean isProviderExistsByWebSiteLink(String webSiteLink) {
        Objects.requireNonNull(webSiteLink);
        return providerRepository.existsByWebSiteLink(webSiteLink);
    }
}
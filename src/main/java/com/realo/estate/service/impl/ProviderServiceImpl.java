package com.realo.estate.service.impl;

import com.realo.estate.domain.dto.ProviderDto;
import com.realo.estate.domain.mapper.ProviderMapper;
import com.realo.estate.domain.persistence.estate.Provider;
import com.realo.estate.exception.ResourceNotFoundException;
import com.realo.estate.repository.ProviderRepository;
import com.realo.estate.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;
    private static final String PROVIDER_CREDENTIALS_ALREADY_EXISTS = "Provider with this name or website link already exists!";
    private static final String RESOURCE_NOT_FOUND_MESSAGE = "Resource Not Found! Please try again.";

    @Transactional
    @Override
    public ProviderDto save(ProviderDto providerDto) {
        if (!providerRepository.existsByName(providerDto.getName())
            && !providerRepository.existsByWebSiteLink(providerDto.getWebSiteLink())) {
            return Optional.of(providerDto)
                    .map(providerMapper::toEntity)
                    .map(providerRepository::save)
                    .map(providerMapper::toDto)
                    .orElseThrow();
        }
        throw new IllegalStateException(PROVIDER_CREDENTIALS_ALREADY_EXISTS);
    }

    @Transactional
    @Override
    public ProviderDto update(Long id, ProviderDto providerDto) {
        Objects.requireNonNull(providerDto);
        return providerRepository.findById(id)
                .map(entity -> {
                    Provider provider = providerMapper.toEntity(providerDto);
                    provider.setId(providerDto.getId());
                    return provider;
                })
                .map(providerRepository::saveAndFlush)
                .map(providerMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND_MESSAGE));
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        return providerRepository.findById(id)
                .map(provider -> {
                    providerRepository.delete(provider);
                    providerRepository.flush();
                    return true;
                }).orElse(false);
    }

    @Transactional(readOnly = true)
    @Override
    public ProviderDto findById(Long id) {
        return providerRepository.findById(id)
                .map(providerMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND_MESSAGE));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProviderDto> findAll() {
        return providerRepository.findAll().stream()
                .map(providerMapper::toDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    @Override
    public ProviderDto findByName(String name) {
        return providerRepository.findByName(name)
                .map(providerMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND_MESSAGE));
    }
}
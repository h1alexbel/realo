package com.realo.estate.service.impl;

import com.realo.estate.domain.estate.Provider;
import com.realo.estate.dto.ProviderDto;
import com.realo.estate.exception.ClientStateException;
import com.realo.estate.exception.ResourceNotFoundException;
import com.realo.estate.mapper.ProviderMapper;
import com.realo.estate.repository.ProviderRepository;
import com.realo.estate.service.ProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

  private static final String PROVIDER_CREDENTIALS_ALREADY_EXISTS = "Provider with this name or website link already exists!";
  private static final String PROVIDER_NOT_FOUND_MESSAGE = "Provider Not Found! Please try again.";
  public static final String PROVIDER_SAVED_IN_SERVICE = "Provider was saved in service :{}";
  private static final String PROVIDER_UPDATED_IN_SERVICE = "Provider was updated in service :{}";
  private static final String PROVIDER_DELETED_IN_SERVICE = "Provider was deleted in service :{}";
  private final ProviderRepository providerRepository;
  private final ProviderMapper providerMapper;

  @Transactional
  @Override
  public ProviderDto save(ProviderDto providerDto) {
    if (!providerRepository.existsByName(providerDto.getName())
        && !providerRepository.existsByWebSiteLink(providerDto.getWebSiteLink())) {
      ProviderDto saved = Optional.of(providerDto)
          .map(providerMapper::toEntity)
          .map(providerRepository::save)
          .map(providerMapper::toDto)
          .orElseThrow();
      log.debug(PROVIDER_SAVED_IN_SERVICE, saved);
      return saved;
    }
    throw new ClientStateException(PROVIDER_CREDENTIALS_ALREADY_EXISTS);
  }

  @Transactional
  @Override
  public ProviderDto update(Long id, ProviderDto providerDto) {
    Objects.requireNonNull(providerDto);
    if (providerRepository.existsByName(providerDto.getName())
        && providerRepository.existsByWebSiteLink(providerDto.getWebSiteLink())) {
      throw new ClientStateException(PROVIDER_CREDENTIALS_ALREADY_EXISTS);
    }
    ProviderDto updated = providerRepository.findById(id)
        .map(entity -> {
          Provider provider = providerMapper.toEntity(providerDto);
          provider.setId(id);
          return provider;
        })
        .map(providerRepository::saveAndFlush)
        .map(providerMapper::toDto)
        .orElseThrow(() -> new ResourceNotFoundException(PROVIDER_NOT_FOUND_MESSAGE));
    log.debug(PROVIDER_UPDATED_IN_SERVICE, updated);
    return updated;
  }

  @Transactional
  @Override
  public boolean deleteById(Long id) {
    return providerRepository.findById(id)
        .map(provider -> {
          providerRepository.delete(provider);
          providerRepository.flush();
          log.debug(PROVIDER_DELETED_IN_SERVICE, provider);
          return true;
        }).orElse(false);
  }

  @Override
  public ProviderDto findById(Long id) {
    return providerRepository.findById(id)
        .map(providerMapper::toDto)
        .orElseThrow(() -> new ResourceNotFoundException(PROVIDER_NOT_FOUND_MESSAGE));
  }

  @Override
  public List<ProviderDto> findAll() {
    return providerRepository.findAll().stream()
        .map(providerMapper::toDto)
        .collect(toList());
  }

  @Override
  public ProviderDto findByName(String name) {
    return providerRepository.findByName(name)
        .map(providerMapper::toDto)
        .orElseThrow(() -> new ResourceNotFoundException(PROVIDER_NOT_FOUND_MESSAGE));
  }
}
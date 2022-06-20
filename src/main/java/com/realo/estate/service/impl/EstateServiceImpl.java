package com.realo.estate.service.impl;

import com.realo.estate.domain.dto.EstateDto;
import com.realo.estate.domain.mapper.EstateMapper;
import com.realo.estate.domain.persistence.estate.Estate;
import com.realo.estate.domain.persistence.estate.EstateType;
import com.realo.estate.exception.ResourceNotFoundException;
import com.realo.estate.repository.EstateRepository;
import com.realo.estate.repository.filter.EstateFilter;
import com.realo.estate.service.EstateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EstateServiceImpl implements EstateService {

    private final EstateRepository estateRepository;
    private final EstateMapper estateMapper;
    private static final String ESTATE_NOT_FOUND_MESSAGE = "Estate Not Found! Please try again.";

    @Transactional
    @Override
    public EstateDto save(EstateDto estateDto) {
        return Optional.of(estateDto)
                .map(estateMapper::toEntity)
                .map(estateRepository::save)
                .map(estateMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    @Override
    public EstateDto update(Long id, EstateDto estateDto) {
        return estateRepository.findById(id)
                .map(entity -> {
                    Estate estate = estateMapper.toEntity(estateDto);
                    estate.setId(estateDto.getId());
                    return estate;
                })
                .map(estateRepository::saveAndFlush)
                .map(estateMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(ESTATE_NOT_FOUND_MESSAGE));
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        return estateRepository.findById(id)
                .map(estate -> {
                    estateRepository.delete(estate);
                    estateRepository.flush();
                    return true;
                }).orElse(false);
    }

    @Transactional
    @Override
    public void updateEstateTypeById(EstateType estateTypeToSet, Long id) {
        Optional<Estate> maybeEstate = estateRepository.findById(id);
        maybeEstate.ifPresent(estate ->
                estateRepository.updateEstateTypeById(estateTypeToSet, estate.getId()));
    }

    @Transactional(readOnly = true)
    @Override
    public List<EstateDto> findAll(EstateFilter filter) {
        return estateRepository.findByFilter(filter).stream()
                .map(estateMapper::toDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<EstateDto> findAllByEstateType(EstateType estateType) {
        return estateRepository.findAllByEstateType(estateType).stream()
                .map(estateMapper::toDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<EstateDto> findAllByYearOfConstruction(Integer year) {
        return estateRepository
                .findAllByYearOfConstruction(year).stream()
                .map(estateMapper::toDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<EstateDto> findAllBySquare(Double square) {
        return estateRepository.findAllBySquare(square).stream()
                .map(estateMapper::toDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    @Override
    public EstateDto findById(Long id) {
        return estateRepository.findById(id)
                .map(estateMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(""));
    }

    @Transactional(readOnly = true)
    @Override
    public List<EstateDto> findAll() {
        return estateRepository.findAll().stream()
                .map(estateMapper::toDto)
                .collect(toList());
    }
}
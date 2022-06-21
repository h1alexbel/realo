package com.realo.estate.service.impl;

import com.realo.estate.domain.dto.AnnouncementDto;
import com.realo.estate.domain.mapper.AnnouncementMapper;
import com.realo.estate.domain.persistence.announcement.Announcement;
import com.realo.estate.domain.persistence.announcement.AnnouncementType;
import com.realo.estate.exception.ResourceNotFoundException;
import com.realo.estate.repository.AnnouncementRepository;
import com.realo.estate.repository.filter.AnnouncementFilter;
import com.realo.estate.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;
    private static final String ANNOUNCEMENT_NOT_FOUND_MESSAGE = "Announcement Not Found! Please try again.";
    private static final String ANNOUNCEMENT_CREDENTIALS_ALREADY_EXISTS = "Announcement with this title already exists!";

    @Transactional
    @Override
    public AnnouncementDto save(AnnouncementDto announcementDto) {
        Objects.requireNonNull(announcementDto.getTitle());
        if (!announcementRepository.existsByTitle(announcementDto.getTitle())) {
            return Optional.of(announcementDto)
                    .map(announcementMapper::toEntity)
                    .map(announcementRepository::save)
                    .map(announcementMapper::toDto)
                    .orElseThrow();
        }
        throw new IllegalStateException(ANNOUNCEMENT_CREDENTIALS_ALREADY_EXISTS);
    }

    @Transactional
    @Override
    public AnnouncementDto update(Long id, AnnouncementDto announcementDto) {
        return announcementRepository.findById(id)
                .map(entity -> {
                    Announcement announcement = announcementMapper.toEntity(announcementDto);
                    announcement.setId(announcement.getId());
                    return announcement;
                })
                .map(announcementRepository::saveAndFlush)
                .map(announcementMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(ANNOUNCEMENT_NOT_FOUND_MESSAGE));
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        return announcementRepository.findById(id)
                .map(announcement -> {
                    announcementRepository.delete(announcement);
                    announcementRepository.flush();
                    return true;
                }).orElse(false);
    }

    @Transactional
    @Override
    public void updateAnnouncementTypeById(AnnouncementType announcementType, Long id) {
        Optional<Announcement> maybeAnnouncement = announcementRepository.findById(id);
        maybeAnnouncement.ifPresent(announcement ->
                announcementRepository
                        .updateAnnouncementTypeById(announcementType, announcement.getId()));
    }

    @Transactional(readOnly = true)
    @Override
    public AnnouncementDto findByTitle(String title) {
        return announcementRepository.findByTitle(title)
                .map(announcementMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(ANNOUNCEMENT_NOT_FOUND_MESSAGE));
    }

    @Transactional(readOnly = true)
    @Override
    public List<AnnouncementDto> findAll() {
        return announcementRepository.findAll().stream()
                .map(announcementMapper::toDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<AnnouncementDto> findAll(AnnouncementFilter filter) {
        return announcementRepository.findByFilter(filter).stream()
                .map(announcementMapper::toDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<AnnouncementDto> findAllByTitleContaining(String title) {
        return announcementRepository.findAllByTitleContaining(title).stream()
                .map(announcementMapper::toDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<AnnouncementDto> findAllByAnnouncementType(AnnouncementType type) {
        return announcementRepository.findAllByAnnouncementType(type).stream()
                .map(announcementMapper::toDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<AnnouncementDto> findAllLikedByUserLogin(String login) {
        return announcementRepository.findAllLikedByUserLogin(login).stream()
                .map(announcementMapper::toDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<AnnouncementDto> findAllByCreatedAtBetween(LocalDate from, LocalDate to) {
        return announcementRepository.findAllByCreatedAtBetween(from, to).stream()
                .map(announcementMapper::toDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    @Override
    public AnnouncementDto findById(Long id) {
        return announcementRepository.findById(id)
                .map(announcementMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(ANNOUNCEMENT_NOT_FOUND_MESSAGE));
    }
}
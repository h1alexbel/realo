package com.realo.estate.web.controller;

import com.realo.estate.dto.AnnouncementDto;
import com.realo.estate.dto.AnnouncementFilter;
import com.realo.estate.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@Slf4j
@RestController
@RequestMapping("/api/v1/announcements")
@RequiredArgsConstructor
public class AnnouncementRestController {

  private static final String ANNOUNCEMENT_WAS_SAVED_IN_CONTROLLER = "Announcement was saved in controller :{}";
  private static final String ANNOUNCEMENT_WAS_UPDATED_IN_CONTROLLER = "Announcement was updated in controller :{}";
  private static final String ANNOUNCEMENT_WITH_ID_WAS_DELETED_IN_CONTROLLER = "Announcement with id: {} was deleted in controller";
  private final AnnouncementService announcementService;

  @PreAuthorize("hasAnyAuthority('AGENT', 'USER')")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public AnnouncementDto createAnnouncement(@RequestBody @Validated AnnouncementDto announcementDto) {
    AnnouncementDto saved = announcementService.save(announcementDto);
    log.debug(ANNOUNCEMENT_WAS_SAVED_IN_CONTROLLER, saved);
    return saved;
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PutMapping("/{id}")
  public AnnouncementDto update(
      @PathVariable Long id,
      @RequestBody @Validated AnnouncementDto announcementToUpdate) {
    AnnouncementDto updated = announcementService.update(id, announcementToUpdate);
    log.debug(ANNOUNCEMENT_WAS_UPDATED_IN_CONTROLLER, updated);
    return updated;
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Long id) {
    ResponseEntity<Object> response = announcementService.deleteById(id)
        ? noContent().build()
        : notFound().build();
    log.debug(ANNOUNCEMENT_WITH_ID_WAS_DELETED_IN_CONTROLLER, id);
    return response;
  }

  @GetMapping
  public List<AnnouncementDto> getAll(@RequestBody AnnouncementFilter filter) {
    return announcementService.findAll(filter);
  }

  @GetMapping("/{id}")
  public AnnouncementDto getById(@PathVariable Long id) {
    return announcementService.findById(id);
  }

  @GetMapping("/title")
  public List<AnnouncementDto> getAllByTitleContaining(@RequestParam String title) {
    return announcementService.findAllByTitleContaining(title);
  }

  @GetMapping("/likes/user")
  public List<AnnouncementDto> getAllByLikesFromUser(@RequestParam String login) {
    return announcementService.findAllLikedByUserLogin(login);
  }
}
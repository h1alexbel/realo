package com.realo.estate.web.controller.rest;

import com.realo.estate.domain.dto.AnnouncementDto;
import com.realo.estate.domain.persistence.announcement.AnnouncementType;
import com.realo.estate.repository.filter.AnnouncementFilter;
import com.realo.estate.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

@RestController
@RequestMapping("/api/v1/announcements")
@RequiredArgsConstructor
public class AnnouncementRestController {

    private final AnnouncementService announcementService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnnouncementDto createAnnouncement(@RequestBody AnnouncementDto announcementDto) {
        return announcementService.save(announcementDto);
    }

    @PutMapping("/{id}")
    public AnnouncementDto update(
            @PathVariable Long id,
            @RequestBody AnnouncementDto announcementToUpdate) {
        return announcementService.update(id, announcementToUpdate);
    }

    @PatchMapping("/{id}/{type}")
    public void updateType(@PathVariable Long id, @PathVariable AnnouncementType type) {
        announcementService.updateAnnouncementTypeById(type, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return announcementService.deleteById(id)
                ? noContent().build()
                : notFound().build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AnnouncementDto> getAll() {
        return announcementService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/filter")
    public List<AnnouncementDto> getAll(@RequestBody AnnouncementFilter filter) {
        return announcementService.findAll(filter);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public AnnouncementDto getById(@PathVariable Long id) {
        return announcementService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/q")
    public List<AnnouncementDto> getAllByTitleContaining(@RequestParam String title) {
        return announcementService.findAllByTitleContaining(title);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/type")
    public List<AnnouncementDto> getAllByType(AnnouncementType type) {
        return announcementService.findAllByAnnouncementType(type);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/likes/user")
    public List<AnnouncementDto> getAllByLikesFromUser(@RequestParam String login) {
        return announcementService.findAllLikedByUserLogin(login);
    }
}
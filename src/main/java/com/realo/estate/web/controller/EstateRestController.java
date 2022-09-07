package com.realo.estate.web.controller;

import com.realo.estate.domain.estate.EstateType;
import com.realo.estate.dto.EstateDto;
import com.realo.estate.dto.EstateFilter;
import com.realo.estate.service.EstateService;
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
@RequestMapping("/api/v1/estates")
@RequiredArgsConstructor
public class EstateRestController {

  private static final String ESTATE_WAS_SAVED_IN_CONTROLLER = "Estate was saved in controller :{}";
  private static final String ESTATE_WAS_UPDATED_IN_CONTROLLER = "Estate was updated in controller :{}";
  private static final String ESTATE_WITH_ID_WAS_DELETED_IN_CONTROLLER = "Estate with id: {} was deleted in controller";
  private final EstateService estateService;

  @PreAuthorize("hasAuthority('AGENT')")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public EstateDto create(@RequestBody @Validated EstateDto estateDto) {
    EstateDto saved = estateService.save(estateDto);
    log.debug(ESTATE_WAS_SAVED_IN_CONTROLLER, saved);
    return saved;
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @PutMapping("/{id}")
  public EstateDto update(
      @PathVariable Long id, @RequestBody @Validated EstateDto estateDto) {
    EstateDto updated = estateService.update(id, estateDto);
    log.debug(ESTATE_WAS_UPDATED_IN_CONTROLLER, updated);
    return updated;
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Long id) {
    ResponseEntity<Object> response = estateService.deleteById(id)
        ? noContent().build()
        : notFound().build();
    log.debug(ESTATE_WITH_ID_WAS_DELETED_IN_CONTROLLER, id);
    return response;
  }

  @PreAuthorize("hasAuthority('AGENT')")
  @GetMapping
  public List<EstateDto> getAll(@RequestBody EstateFilter filter) {
    return estateService.findAll(filter);
  }

  @PreAuthorize("hasAuthority('AGENT')")
  @GetMapping("/{id}")
  public EstateDto getById(@PathVariable Long id) {
    return estateService.findById(id);
  }

  @PreAuthorize("hasAuthority('AGENT')")
  @GetMapping("/type")
  public List<EstateDto> getAllByType(@RequestParam EstateType type) {
    return estateService.findAllByEstateType(type);
  }
}

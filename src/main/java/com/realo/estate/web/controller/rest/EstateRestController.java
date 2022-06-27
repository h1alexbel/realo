package com.realo.estate.web.controller.rest;

import com.realo.estate.domain.dto.EstateDto;
import com.realo.estate.domain.persistence.estate.EstateType;
import com.realo.estate.repository.filter.EstateFilter;
import com.realo.estate.service.EstateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

@Slf4j
@RestController
@RequestMapping("/api/v1/estates")
@RequiredArgsConstructor
public class EstateRestController {

    private final EstateService estateService;
    private static final String ESTATE_WAS_SAVED_IN_CONTROLLER = "Estate was saved in controller :{}";
    private static final String ESTATE_WAS_UPDATED_IN_CONTROLLER = "Estate was updated in controller :{}";
    private static final String ESTATE_WITH_ID_HAS_UPDATED_TYPE = "Estate with id :{}, has updated type :{}";
    private static final String ESTATE_WITH_ID_WAS_DELETED_IN_CONTROLLER = "Estate with id: {} was deleted in controller";

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EstateDto create(@RequestBody @Validated EstateDto estateDto) {
        EstateDto saved = estateService.save(estateDto);
        log.info(ESTATE_WAS_SAVED_IN_CONTROLLER, saved);
        return saved;
    }

    @PutMapping("/{id}")
    public EstateDto update(
            @PathVariable Long id, @RequestBody @Validated EstateDto estateDto) {
        EstateDto updated = estateService.update(id, estateDto);
        log.info(ESTATE_WAS_UPDATED_IN_CONTROLLER, updated);
        return updated;
    }

    @PatchMapping("/{id}/{type}")
    public void updateType(@PathVariable Long id, @PathVariable EstateType type) {
        estateService.updateEstateTypeById(type, id);
        log.info(ESTATE_WITH_ID_HAS_UPDATED_TYPE, id, type);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        ResponseEntity<Object> response = estateService.deleteById(id)
                ? noContent().build()
                : notFound().build();
        log.info(ESTATE_WITH_ID_WAS_DELETED_IN_CONTROLLER, id);
        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<EstateDto> getAll() {
        return estateService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/filter")
    public List<EstateDto> getAll(@RequestBody EstateFilter filter) {
        return estateService.findAll(filter);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public EstateDto getById(@PathVariable Long id) {
        return estateService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/type")
    public List<EstateDto> getAllByType(@RequestParam EstateType type) {
        return estateService.findAllByEstateType(type);
    }
}
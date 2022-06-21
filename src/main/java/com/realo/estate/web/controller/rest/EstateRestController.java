package com.realo.estate.web.controller.rest;

import com.realo.estate.domain.dto.EstateDto;
import com.realo.estate.domain.persistence.estate.EstateType;
import com.realo.estate.repository.filter.EstateFilter;
import com.realo.estate.service.EstateService;
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
@RequestMapping("/api/v1/estates")
@RequiredArgsConstructor
public class EstateRestController {

    private final EstateService estateService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EstateDto create(@RequestBody EstateDto estateDto) {
        return estateService.save(estateDto);
    }

    @PutMapping("/{id}")
    public EstateDto update(@PathVariable Long id, @RequestBody EstateDto estateDto) {
        return estateService.update(id, estateDto);
    }

    @PatchMapping("/{id}/{type}")
    public void updateType(@PathVariable Long id, @PathVariable EstateType type) {
        estateService.updateEstateTypeById(type, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return estateService.deleteById(id)
                ? noContent().build()
                : notFound().build();
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
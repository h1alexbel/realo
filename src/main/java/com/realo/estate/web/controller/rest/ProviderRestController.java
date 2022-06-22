package com.realo.estate.web.controller.rest;

import com.realo.estate.domain.dto.ProviderDto;
import com.realo.estate.service.ProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/v1/providers")
@RequiredArgsConstructor
public class ProviderRestController {

    private final ProviderService providerService;
    private static final String PROVIDER_WAS_SAVED_IN_CONTROLLER = "Provider was saved in controller :{}";
    private static final String PROVIDER_WAS_UPDATED_IN_CONTROLLER = "Provider was updated in controller :{}";

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProviderDto create(@RequestBody ProviderDto providerDto) {
        ProviderService saved = providerService;
        log.debug(PROVIDER_WAS_SAVED_IN_CONTROLLER, saved);
        return saved.save(providerDto);
    }

    @PutMapping("/{id}")
    public ProviderDto update(@PathVariable Long id, @RequestBody ProviderDto providerToUpdate) {
        ProviderDto updated = providerService.update(id, providerToUpdate);
        log.debug(PROVIDER_WAS_UPDATED_IN_CONTROLLER, updated);
        return updated;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return providerService.deleteById(id)
                ? noContent().build()
                : notFound().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ProviderDto> getAll() {
        return providerService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ProviderDto getById(@PathVariable Long id) {
        return providerService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/q")
    public ProviderDto getByName(@RequestParam String name) {
        return providerService.findByName(name);
    }
}
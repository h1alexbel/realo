package com.realo.estate.web.controller.rest;

import com.realo.estate.domain.dto.ProviderDto;
import com.realo.estate.service.ProviderService;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping("/api/v1/providers")
@RequiredArgsConstructor
public class ProviderRestController {

    private final ProviderService providerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProviderDto create(@RequestBody ProviderDto providerDto) {
        return providerService.save(providerDto);
    }

    @PutMapping("/{id}")
    public ProviderDto update(@PathVariable Long id, @RequestBody ProviderDto providerToUpdate) {
        return providerService.update(id, providerToUpdate);
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
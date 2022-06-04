package com.realo.estate.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericCrudService<D extends Serializable, K extends Serializable> {

    D save(D dto);

    void update(D dto);

    void deleteById(K key);

    Optional<D> findById(K id);

    List<D> findAll();
}
package com.realo.estate.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericCrudService<D extends Serializable, K extends Serializable> {

    D save(D dto);

    Optional<D> update(D dto);

    boolean deleteById(K key);

    Optional<D> findById(K id);

    List<D> findAll();
}
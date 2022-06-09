package com.realo.estate.service;

import com.realo.estate.domain.persistence.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericCrudService<E extends BaseEntity<K>, K extends Serializable> {

    E save(E entity);

    void update(E entity);

    boolean deleteById(K key);

    Optional<E> findById(K id);

    List<E> findAll();
}
package com.realo.estate.service;

import java.io.Serializable;
import java.util.List;

public interface GenericCrudService<D extends Serializable, K extends Serializable> {

    D save(D dto);

    D update(K id, D dto);

    boolean deleteById(K id);

    D findById(K id);

    List<D> findAll();
}
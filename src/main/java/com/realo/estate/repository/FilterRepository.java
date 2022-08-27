package com.realo.estate.repository;

import java.util.List;

public interface FilterRepository<T, F> {

  List<T> findByFilter(F filter);
}
package com.realo.estate.repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QPredicates {

  public static QPredicates builder() {
    return new QPredicates();
  }

  private final List<Predicate> predicates = new ArrayList<>();

  public <T> QPredicates add(T obj, Function<T, Predicate> function) {
    if (obj != null) {
      predicates.add(function.apply(obj));
    }
    return this;
  }

  public Predicate build() {
    return Optional.ofNullable(ExpressionUtils.allOf(predicates))
        .orElseGet(() -> Expressions.asBoolean(true).isTrue());
  }

  public Predicate buildOr() {
    return Optional.ofNullable(ExpressionUtils.anyOf(predicates))
        .orElseGet(() -> Expressions.asBoolean(true).isTrue());
  }
}
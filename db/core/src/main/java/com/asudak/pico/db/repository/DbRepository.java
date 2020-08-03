package com.asudak.pico.db.repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface DbRepository<T, ID> {

    Optional<T> findOne(@NotNull ID id);

    List<T> findAll();

    T save(@NotNull T entity);

    void delete(@NotNull T entity);
}

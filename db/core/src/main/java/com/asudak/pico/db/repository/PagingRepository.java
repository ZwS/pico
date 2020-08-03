package com.asudak.pico.db.repository;

import com.asudak.pico.db.model.page.Page;
import com.asudak.pico.db.model.page.PageRequest;

import javax.validation.constraints.NotNull;

public interface PagingRepository<T, ID> extends DbRepository<T, ID> {

    Page<T> findPage(@NotNull PageRequest request);
}

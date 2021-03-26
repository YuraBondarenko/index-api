package com.index.api.service;

import java.util.List;

public interface IDableService<T> {
    T save(T model);

    T getById(Long id);

    List<T> getAll(int page, int limit, String sortBy);

    void deleteAll();
}

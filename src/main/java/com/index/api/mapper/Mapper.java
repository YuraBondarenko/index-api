package com.index.api.mapper;

public interface Mapper<T, S, Q> {
    T getEntity(S dto);

    Q getDto(T entity);
}

package com.wms.service;

import java.util.List;

public interface EntityService<T> {
    List<T> findAll();

    T findById(Long id);

    T update(T entity, Long id);

    T save(T entity);

    void deleteById(Long id);
}

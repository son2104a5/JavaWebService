package com.data.service;

import java.util.List;

public interface IService<T> {
    List<T> findAll();

    T findById(Long id);

    T save(T entity);

    T update(T entity, Long id);

    boolean delete(Long id);
}

package com.data.service;

import java.util.Optional;

public interface IService<T> {
    T save(T entity);
    Optional<T> findById(Long id);
    T update(T entity);
    void delete(Long id);
}

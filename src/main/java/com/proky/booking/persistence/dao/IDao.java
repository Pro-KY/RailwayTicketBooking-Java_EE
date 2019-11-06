package com.proky.booking.persistence.dao;

import java.util.Optional;

public interface IDao<T> {
    Long save(T entity);
    Long update(T entity);
    boolean delete(T entity);
    Optional<T> findById(Long id);
}

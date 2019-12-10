package com.proky.booking.persistence.dao;

import java.util.List;
import java.util.Optional;

/**
 * The interface {@code IDao} used to reduce boilerplate code in all Dao interfaces
 * working with specific entities.
 *
 * @param <T> type of entity object
 */
public interface IDao<T> {

    /**
     * Save entity to a database.
     *
     * @param entity to save
     * @return saved entity identifier in a database
     */
    Long save(T entity);

    /**
     * Update entity in a database.
     *
     * @param entity to save
     * @return updated entity identifier in a database
     */
    Long update(T entity);

    /**
     * Delete entity from a database.
     *
     * @param entity to delete
     * @return {@code true} if entity is deleted, otherwise {@code false}
     */
    boolean delete(T entity);

    /**
     * Find entity from a database by specific id.
     *
     * @param id an identifier of entity object.
     * @return result wrapper, which contains found entity, otherwise null
     */
    Optional<T> findById(Long id);
}

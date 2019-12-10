package com.proky.booking.persistence.dao;

import com.proky.booking.persistence.entity.UserType;

import java.util.Optional;

public interface IUserTypeDao extends IDao<UserType> {

    /**
     * Find user type by specific type value.
     *
     * @param type type of the user
     * @return result wrapper, which hold found user or null
     * @see Optional
     */
    Optional<UserType> findByType(String type);
}

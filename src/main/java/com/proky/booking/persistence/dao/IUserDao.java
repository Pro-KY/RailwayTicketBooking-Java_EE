package com.proky.booking.persistence.dao;

import com.proky.booking.persistence.entity.User;

import java.util.Optional;

public interface IUserDao extends IDao<User> {
    Optional<User> findByEmail(String email);
}

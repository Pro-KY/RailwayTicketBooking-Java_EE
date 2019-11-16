package com.proky.booking.persistence.dao;

import com.proky.booking.persistence.entity.User;
import com.proky.booking.persistence.entity.UserType;

import java.util.List;
import java.util.Optional;

public interface IUserDao extends IDao<User> {
    Optional<User> findByEmail(String email);
    List<User> findAllByType(UserType userType, long pageSize, long offSet);
    Long countAllByType(UserType userType);
}

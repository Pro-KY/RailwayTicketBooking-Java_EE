package com.proky.booking.persistence.dao;

import com.proky.booking.persistence.entity.User;
import com.proky.booking.persistence.entity.UserType;

import java.util.List;
import java.util.Optional;

public interface IUserDao extends IDao<User> {

    /**
     * Find user by specific email.
     *
     * @param email user email
     * @return result wrapper, which hold found user or null
     * @see Optional
     */
    Optional<User> findByEmail(String email);

    /**
     * Find users by user type.
     *
     * @param userType user type
     * @param pageSize amount of entries to retrieve
     * @param offSet offset of the start search point
     * @return list of found users
     */
    List<User> findAllByType(UserType userType, long pageSize, long offSet);

    /**
     * Get users amount by user type.
     *
     * @param userType user type
     * @return amount of users
     */
    Long countAllByType(UserType userType);
}

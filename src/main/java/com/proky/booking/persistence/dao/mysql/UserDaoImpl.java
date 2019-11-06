package com.proky.booking.persistence.dao.mysql;

import com.proky.booking.persistence.dao.IUserDao;
import com.proky.booking.persistence.entity.User;
import com.proky.booking.util.properties.SqlProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserDaoImpl implements IUserDao {
    private static UserDaoImpl instance;
    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        final String findByEmailQuery = SqlProperties.FIND_BY_EMAIL;
        return Optional.empty();
    }

    @Override
    public Long save(User entity) {
        return null;
    }

    @Override
    public Long update(User entity) {
        return null;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }
}

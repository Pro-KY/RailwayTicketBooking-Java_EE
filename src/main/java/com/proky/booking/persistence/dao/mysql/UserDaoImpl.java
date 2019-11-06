package com.proky.booking.persistence.dao.mysql;

import com.proky.booking.persistence.dao.IUserDao;
import com.proky.booking.persistence.entity.User;
import com.proky.booking.persistence.jdbc.JdbcTemplate;
import com.proky.booking.persistence.mapper.UserMapper;
import com.proky.booking.persistence.mapper.UserTypeMapper;
import com.proky.booking.util.properties.SqlProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserDaoImpl implements IUserDao {
    private static UserDaoImpl instance;
    private JdbcTemplate jdbcTemplate;

    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

    public UserDaoImpl() {
        jdbcTemplate = JdbcTemplate.getInstance();
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        final String sqlQuery = SqlProperties.FIND_BY_EMAIL;
        final UserMapper userMapper = new UserMapper(true);
        userMapper.mapUserTypeRelation(new UserTypeMapper(true));
        return jdbcTemplate.findByQuery(sqlQuery, userMapper, email);
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

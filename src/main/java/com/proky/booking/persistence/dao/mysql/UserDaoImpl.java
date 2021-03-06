package com.proky.booking.persistence.dao.mysql;

import com.proky.booking.persistence.dao.IUserDao;
import com.proky.booking.persistence.entity.User;
import com.proky.booking.persistence.entity.UserType;
import com.proky.booking.persistence.jdbc.JdbcTemplate;
import com.proky.booking.persistence.mapper.UserMapper;
import com.proky.booking.persistence.mapper.UserTypeMapper;
import com.proky.booking.util.properties.SqlProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import static com.proky.booking.util.properties.SqlProperties.*;

public class UserDaoImpl implements IUserDao {
    private static UserDaoImpl instance;
    private JdbcTemplate jdbcTemplate;

    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

    private UserDaoImpl() {
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
        final String sqlQuery = SqlProperties.getValue(FIND_USER_BY_EMAIL);
        final UserMapper userMapper = new UserMapper(true);
        userMapper.mapUserTypeRelation(new UserTypeMapper(true));
        return jdbcTemplate.findByQuery(sqlQuery, userMapper, email);
    }

    @Override
    public List<User> findAllByType(UserType userType, long pageSize, long offSet) {
        final String sqlQuery = SqlProperties.getValue(FIND_ALL_USERS_BY_TYPE);
        final UserMapper userMapper = new UserMapper(false);
        return jdbcTemplate.findAll(sqlQuery, userMapper, userType.getId(), pageSize, offSet);
    }

    @Override
    public Long countAllByType(UserType userType) {
        final String sqlQuery = SqlProperties.getValue(COUNT_ALL_USERS_BY_TYPE);
        return jdbcTemplate.countRows(sqlQuery, userType.getId());
    }

    @Override
    public Long save(User entity) {
        Object[] params = {entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getPassword(), entity.getUserType().getId()};
        String sqlQuery = SqlProperties.getValue(SAVE_USER);
        return jdbcTemplate.saveOrUpdate(sqlQuery, params);
    }

    @Override
    public Long update(User entity) {
        Object[] params = {entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getPassword(), entity.getUserType().getId(), entity.getId()};
        String sqlQuery = SqlProperties.getValue(UPDATE_USER);
        return jdbcTemplate.saveOrUpdate(sqlQuery, params);
    }

    @Override
    public boolean delete(User entity) {
        final String sqlQuery = SqlProperties.getValue(DELETE_USER_BY_ID);
        return jdbcTemplate.delete(sqlQuery, entity.getId());
    }

    @Override
    public Optional<User> findById(Long id) {
        final String sqlQuery = SqlProperties.getValue(FIND_USER_BY_ID);
        final UserMapper userMapper = new UserMapper(false);
        return jdbcTemplate.findByQuery(sqlQuery, userMapper, id);
    }
}

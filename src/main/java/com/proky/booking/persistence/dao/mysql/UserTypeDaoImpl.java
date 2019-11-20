package com.proky.booking.persistence.dao.mysql;

import com.proky.booking.persistence.dao.IUserTypeDao;
import com.proky.booking.persistence.entity.UserType;
import com.proky.booking.persistence.jdbc.JdbcTemplate;
import com.proky.booking.persistence.mapper.InvoiceMapper;
import com.proky.booking.persistence.mapper.TrainMapper;
import com.proky.booking.persistence.mapper.UserMapper;
import com.proky.booking.persistence.mapper.UserTypeMapper;
import com.proky.booking.util.properties.SqlProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.proky.booking.util.properties.SqlProperties.*;

public class UserTypeDaoImpl implements IUserTypeDao {
    private JdbcTemplate jdbcTemplate;
    private static UserTypeDaoImpl instance;
    private static final Logger log = LogManager.getLogger(UserTypeDaoImpl.class);

    private UserTypeDaoImpl() {
        this.jdbcTemplate = JdbcTemplate.getInstance();
    }

    public static UserTypeDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserTypeDaoImpl();
        }
        return instance;
    }

    @Override
    public Optional<UserType> findByType(String type) {
        final String sqlQuery = SqlProperties.getValue(FIND_USER_TYPE_BY_TYPE);
        final UserTypeMapper userMapper = new UserTypeMapper(false);
        return jdbcTemplate.findByQuery(sqlQuery, userMapper, type);
    }

    @Override
    public Long save(UserType entity) {
        final String query = SqlProperties.getValue(SAVE_USER_TYPE);
        return jdbcTemplate.saveOrUpdate(query, entity.getType());
    }

    @Override
    public Long update(UserType entity) {
        final String query = SqlProperties.getValue(UPDATE_USER_TYPE_BY_ID);
        return jdbcTemplate.saveOrUpdate(query, entity.getType(), entity.getId());
    }

    @Override
    public boolean delete(UserType entity) {
        final String sqlQuery = SqlProperties.getValue(DELETE_USER_TYPE_BY_ID);
        return jdbcTemplate.delete(sqlQuery, entity.getId());
    }

    @Override
    public Optional<UserType> findById(Long id) {
        final String sqlQuery = SqlProperties.getValue(FIND_USER_TYPE_BY_ID);
        final UserTypeMapper userTypeMapper = new UserTypeMapper(false);
        return jdbcTemplate.findByQuery(sqlQuery, userTypeMapper, id);
    }
}

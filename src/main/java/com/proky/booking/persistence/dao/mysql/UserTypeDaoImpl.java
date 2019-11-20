package com.proky.booking.persistence.dao.mysql;

import com.proky.booking.persistence.dao.IUserTypeDao;
import com.proky.booking.persistence.entity.UserType;
import com.proky.booking.persistence.jdbc.JdbcTemplate;
import com.proky.booking.persistence.mapper.UserTypeMapper;
import com.proky.booking.util.properties.SqlProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.proky.booking.util.properties.SqlProperties.FIND_USER_TYPE_BY_TYPE;

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
    public Long save(UserType entity) {
        return null;
    }

    @Override
    public Optional<UserType> findByType(String type) {
        final String sqlQuery = SqlProperties.getValue(FIND_USER_TYPE_BY_TYPE);
        final UserTypeMapper userMapper = new UserTypeMapper(false);
        return jdbcTemplate.findByQuery(sqlQuery, userMapper, type);
    }

    @Override
    public Long update(UserType entity) {
        return null;
    }

    @Override
    public boolean delete(UserType entity) {
        return false;
    }

    @Override
    public Optional<UserType> findById(Long id) {
        return null;
    }
}

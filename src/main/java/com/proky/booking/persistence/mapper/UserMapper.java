package com.proky.booking.persistence.mapper;


import com.proky.booking.persistence.entity.User;
import com.proky.booking.persistence.entity.UserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper extends EntityMapper<User> {
    private static final Logger log = LogManager.getLogger(UserMapper.class);

    private static final String ID = "id";
    private static final String USER_ALIAS_IN_JOIN = "u_id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String USER_TYPE_ID = "user_type_id";

    private boolean mapUserType;
    private EntityMapper<UserType> userTypeEntityMapper;

    public UserMapper(boolean useInJoin) {
        String idColumn = useInJoin ? USER_ALIAS_IN_JOIN : ID;
        columnNames = new String[]{idColumn, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USER_TYPE_ID};
    }

    @Override
    public User mapToEntity(ResultSet resultSet) {

        try {
            long id = resultSet.getLong(columnNames[0]);
            final String firstName = resultSet.getString(columnNames[1]);
            final String lastName = resultSet.getString(columnNames[2]);
            final String email = resultSet.getString(columnNames[3]);
            final String password = resultSet.getString(columnNames[4]);
            final long userTypeId = resultSet.getLong(columnNames[5]);
            final UserType userType = new UserType(userTypeId);

            mappedEntity = new User(id, firstName, lastName, email, password, userType);

            if (mapUserType) {
                mappedEntity.setUserType(userTypeEntityMapper.mapToEntity(resultSet));
            }
        } catch (SQLException e) {
            log.error("Could not map to User entity", e);
        }
        return mappedEntity;
    }

    public void mapUserTypeRelation(EntityMapper<UserType> userTypeEntityMapper) {
        this.userTypeEntityMapper = userTypeEntityMapper;
        mapUserType = true;
    }
}

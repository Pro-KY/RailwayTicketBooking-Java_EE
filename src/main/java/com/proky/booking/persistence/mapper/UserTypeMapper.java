package com.proky.booking.persistence.mapper;

import com.proky.booking.persistence.dao.mysql.UserDaoImpl;
import com.proky.booking.persistence.entity.UserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTypeMapper extends EntityMapper<UserType> {
    private static final Logger log = LogManager.getLogger(UserTypeMapper.class);

    private static final String ID = "id";
    private static final String ID_IN_JOIN = "ut_id";
    private static final String TYPE = "type";

    public UserTypeMapper(boolean useInJoin) {
        String idColumn = useInJoin ? ID_IN_JOIN : ID;
        columnNames = new String[]{idColumn, TYPE};
    }

    @Override
    public UserType mapToEntity(ResultSet resultSet) {
        try {
            long id = resultSet.getLong(columnNames[0]);
            final String type = resultSet.getString(columnNames[1]);
            mappedEntity =  new UserType(id, type);
        } catch (SQLException e) {
            log.error("Could not map to UserType entity", e);
        }
        return mappedEntity;
    }
}

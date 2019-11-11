package com.proky.booking.persistence.mapper;


import com.proky.booking.persistence.entity.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StationMapper extends EntityMapper<Station> {
    private static final Logger log = LogManager.getLogger(StationMapper.class);

    private static final String ID = "id";
    private static final String ID_ALIAS_IN_JOIN = "s_id";
    private static final String NAME = "name";

    public StationMapper(boolean useInJoin) {
        String idColumn = useInJoin ? ID_ALIAS_IN_JOIN : ID;
        columnNames = new String[]{idColumn, NAME};
    }

    @Override
    public Station mapToEntity(ResultSet resultSet) {

        try {
            long id = resultSet.getLong(columnNames[0]);
            final String name = resultSet.getString(columnNames[1]);

            mappedEntity = new Station(id, name);
        } catch (SQLException e) {
            log.error("Could not map to Station entity", e);
        }
        return mappedEntity;
    }
}

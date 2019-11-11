package com.proky.booking.persistence.mapper;

import com.proky.booking.persistence.entity.Route;
import com.proky.booking.persistence.entity.UserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class RouteMapper extends EntityMapper<Route> {
    private static final Logger log = LogManager.getLogger(RouteMapper.class);

    private static final String ID = "id";
    private static final String ROUTE_ALIAS_IN_JOIN = "r_id";
    private static final String NAME = "name";
    private static final String DEPARTURE_DATE = "departure_date";
    private static final String ARRIVAL_DATE = "arrival_date";
    private static final String DEPARTURE_TIME = "departure_time";
    private static final String ARRIVAL_TIME = "arrival_time";
    private static final String ROUTE_LENGTH_FACTOR = "route_length_factor";

    public RouteMapper(boolean useInJoin) {
        String idColumn = useInJoin ? ROUTE_ALIAS_IN_JOIN : ID;
        columnNames = new String[]{idColumn, NAME, DEPARTURE_DATE, ARRIVAL_DATE, DEPARTURE_TIME, ARRIVAL_TIME, ROUTE_LENGTH_FACTOR};
    }

    @Override
    public Route mapToEntity(ResultSet resultSet) {

        try {
            final long id = resultSet.getLong(columnNames[0]);
            final String name = resultSet.getString(columnNames[1]);
            final Date departureDate = resultSet.getDate(columnNames[2]);
            final Date arrivalDate = resultSet.getDate(columnNames[3]);
            final Time departureTime = resultSet.getTime(columnNames[4]);
            final Time arrivalTime = resultSet.getTime(columnNames[5]);
            final Double routeLengthFactor = resultSet.getDouble(columnNames[6]);

            mappedEntity = new Route(id, name, departureDate, arrivalDate, departureTime, arrivalTime, routeLengthFactor);
        } catch (SQLException e) {
            log.error("Could not map to Train entity", e);
        }
        return mappedEntity;
    }
}

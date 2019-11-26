package com.proky.booking.persistence.mapper;

import com.proky.booking.persistence.entity.Route;
import com.proky.booking.persistence.entity.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class RouteMapper extends EntityMapper<Route> {
    private static final Logger log = LogManager.getLogger(RouteMapper.class);

    private static final String ID = "id";
    private static final String ID_ALIAS_IN_JOIN = "r_id";
    private static final String DEPARTURE_STATION_ID = "departure_station_id";
    private static final String ARRIVAL_STATION_ID = "arrival_station_id";
    private static final String DEPARTURE_DATE = "departure_date";
    private static final String ARRIVAL_DATE = "arrival_date";
    private static final String DEPARTURE_TIME = "departure_time";
    private static final String ARRIVAL_TIME = "arrival_time";
    private static final String ROUTE_LENGTH_FACTOR = "route_length_factor";

    private boolean mapDeparutureStation;
    private boolean mapArrivalStation;

    private EntityMapper<Station> stationEntityMapper;

    public RouteMapper(boolean useInJoin) {
        String idColumn = useInJoin ? ID_ALIAS_IN_JOIN : ID;
        columnNames = new String[]{idColumn, DEPARTURE_STATION_ID, ARRIVAL_STATION_ID, DEPARTURE_DATE, ARRIVAL_DATE, DEPARTURE_TIME, ARRIVAL_TIME, ROUTE_LENGTH_FACTOR};
    }

    @Override
    public Route mapToEntity(ResultSet resultSet) {

        try {
            final long id = resultSet.getLong(columnNames[0]);
            final long departureStationId = resultSet.getLong(columnNames[1]);
            final long arrivalStationId = resultSet.getLong(columnNames[2]);
            final Date departureDate = resultSet.getDate(columnNames[3]);
            final Date arrivalDate = resultSet.getDate(columnNames[4]);
            final Time departureTime = resultSet.getTime(columnNames[5]);
            final Time arrivalTime = resultSet.getTime(columnNames[6]);
            final Double routeLengthFactor = resultSet.getDouble(columnNames[7]);
            Station departureStation;
            Station arrivalStation;

            if (mapDeparutureStation) {
                stationEntityMapper.setColumnNameAliasAtIndex("d_s_id", 0);
                stationEntityMapper.setColumnNameAliasAtIndex("d_s_name", 1);
                departureStation = stationEntityMapper.mapToEntity(resultSet);
            } else {
                departureStation = new Station(departureStationId);
            }

            if (mapArrivalStation) {
                stationEntityMapper.setColumnNameAliasAtIndex("a_s_id", 0);
                stationEntityMapper.setColumnNameAliasAtIndex("a_s_name", 1);
                arrivalStation = stationEntityMapper.mapToEntity(resultSet);
            } else {
                arrivalStation = new Station(arrivalStationId);
            }

            mappedEntity = new Route(id, departureStation, arrivalStation, departureDate, arrivalDate, departureTime, arrivalTime, routeLengthFactor);
        } catch (SQLException e) {
            log.error("Could not map to Route entity", e);
        }
        return mappedEntity;
    }

    public void mapDepartureArrivalStationRelations(EntityMapper<Station> stationEntityMapper) {
        this.stationEntityMapper = stationEntityMapper;
        mapDeparutureStation = true;
        mapArrivalStation = true;
    }
}

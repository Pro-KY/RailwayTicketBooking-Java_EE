package com.proky.booking.persistence.mapper;


import com.proky.booking.persistence.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteStationMapper extends EntityMapper<RouteStation> {
    private static final Logger log = LogManager.getLogger(RouteStationMapper.class);

    private static final String ID = "id";
    private static final String ID_ALIAS_IN_JOIN = "rs_id";
    private static final String ROUTE_ID = "route_id";
    private static final String STATION_ID = "station_id";
    private boolean mapRoute;
    private boolean mapStation;

    private EntityMapper<Route> routeEntityMapper;
    private EntityMapper<Station> stationEntityMapper;

    public RouteStationMapper(boolean useInJoin) {
        String idColumn = useInJoin ? ID_ALIAS_IN_JOIN : ID;
        columnNames = new String[] {idColumn, ROUTE_ID, STATION_ID};
    }

    @Override
    public RouteStation mapToEntity(ResultSet resultSet) {

        try {
            Long id = resultSet.getLong(columnNames[0]);
            final Long routeId = resultSet.getLong(columnNames[1]);
            final Long stationId = resultSet.getLong(columnNames[2]);
            final Route route = mapRoute ? routeEntityMapper.mapToEntity(resultSet) : new Route(routeId);
            final Station station = mapStation ? stationEntityMapper.mapToEntity(resultSet) : new Station(stationId);

            mappedEntity = new RouteStation(id, route, station);
        } catch (SQLException e) {
            log.error("Could not map to RouteStation entity", e);
        }
        return mappedEntity;
    }

    public void mapRouteRelation(EntityMapper<Route> routeEntityMapper) {
        this.routeEntityMapper = routeEntityMapper;
        mapRoute = true;
    }

    public void mapStationRelation(EntityMapper<Station> stationEntityMapper) {
        this.stationEntityMapper = stationEntityMapper;
        mapStation = true;
    }
}

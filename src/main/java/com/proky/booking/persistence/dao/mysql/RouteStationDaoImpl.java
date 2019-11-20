package com.proky.booking.persistence.dao.mysql;

import com.proky.booking.persistence.dao.IRouteStationDao;
import com.proky.booking.persistence.entity.RouteStation;
import com.proky.booking.persistence.jdbc.JdbcTemplate;
import com.proky.booking.persistence.mapper.*;
import com.proky.booking.util.properties.SqlProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import static com.proky.booking.util.properties.SqlProperties.*;

public class RouteStationDaoImpl implements IRouteStationDao {
    private JdbcTemplate jdbcTemplate;
    private static RouteStationDaoImpl instance;
    private static final Logger log = LogManager.getLogger(RouteStationDaoImpl.class);

    private RouteStationDaoImpl() {
        this.jdbcTemplate = JdbcTemplate.getInstance();
    }

    public static RouteStationDaoImpl getInstance() {
        if (instance == null) {
            instance = new RouteStationDaoImpl();
        }
        return instance;
    }

    @Override
    public List<RouteStation> findAllByRouteId(Long routeId) {
        final String sqlQuery = SqlProperties.getValue(FIND_ROUTE_STATION_BY_ROUTE_ID);

        final RouteStationMapper routeStationMapper = new RouteStationMapper(true);
        routeStationMapper.mapStationRelation(new StationMapper(true));

        return jdbcTemplate.findAll(sqlQuery, routeStationMapper, routeId);
    }

    @Override
    public Long save(RouteStation entity) {
        Object[] params = {entity.getRoute().getId(), entity.getStation().getId()};
        final String query = SqlProperties.getValue(SAVE_ROUTE_STATION);
        return jdbcTemplate.saveOrUpdate(query, params);
    }

    @Override
    public Long update(RouteStation entity) {
        Object[] params = {entity.getRoute().getId(), entity.getStation().getId(), entity.getId()};
        String sqlQuery = SqlProperties.getValue(UPDATE_ROUTE_STATION_BY_ID);
        return jdbcTemplate.saveOrUpdate(sqlQuery, params);
    }

    @Override
    public boolean delete(RouteStation entity) {
        final String sqlQuery = SqlProperties.getValue(DELETE_ROUTE_STATION_BY_ID);
        return jdbcTemplate.delete(sqlQuery, entity.getId());
    }

    @Override
    public Optional<RouteStation> findById(Long id) {
        final String sqlQuery = SqlProperties.getValue(FIND_ROUTE_STATION_BY_ID);
        final RouteStationMapper routeStationMapper = new RouteStationMapper(true);
        routeStationMapper.mapRouteRelation(new RouteMapper(true));
        routeStationMapper.mapStationRelation(new StationMapper(true));
        return jdbcTemplate.findByQuery(sqlQuery, routeStationMapper, id);
    }
}

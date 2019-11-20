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
        return null;
    }

    @Override
    public Long update(RouteStation entity) {
        return null;
    }

    @Override
    public boolean delete(RouteStation entity) {
        return false;
    }

    @Override
    public Optional<RouteStation> findById(Long id) {
        return null;
    }
}

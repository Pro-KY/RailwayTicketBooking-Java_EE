package com.proky.booking.persistence.dao.mysql;

import com.proky.booking.persistence.dao.IRouteDao;
import com.proky.booking.persistence.dao.IUserDao;
import com.proky.booking.persistence.entity.Route;
import com.proky.booking.persistence.entity.User;
import com.proky.booking.persistence.entity.UserType;
import com.proky.booking.persistence.jdbc.JdbcTemplate;
import com.proky.booking.persistence.mapper.*;
import com.proky.booking.util.properties.SqlProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import static com.proky.booking.util.properties.SqlProperties.*;

public class RouteDaoImpl implements IRouteDao {
    private static RouteDaoImpl instance;
    private JdbcTemplate jdbcTemplate;

    private static final Logger log = LogManager.getLogger(RouteDaoImpl.class);

    private RouteDaoImpl() {
        jdbcTemplate = JdbcTemplate.getInstance();
    }

    public static RouteDaoImpl getInstance() {
        if (instance == null) {
            instance = new RouteDaoImpl();
        }
        return instance;
    }

    @Override
    public Long save(Route entity) {
        Object[] params = {entity.getDepartureStation().getId(), entity.getArrivalStation().getId(), entity.getDepartureDate(), entity.getArrivalDate(),
                entity.getDepartureTime(), entity.getArrivalTime(), entity.getRouteLengthFactor()};
        String sqlQuery = SqlProperties.getValue(SAVE_ROUTE);
        return jdbcTemplate.saveOrUpdate(sqlQuery, params);
    }

    @Override
    public Long update(Route entity) {
        Object[] params = {entity.getDepartureStation().getId(), entity.getArrivalStation().getId(), entity.getDepartureDate(), entity.getArrivalDate(),
        entity.getDepartureTime(), entity.getArrivalTime(), entity.getRouteLengthFactor(), entity.getId()};
        String sqlQuery = SqlProperties.getValue(UPDATE_ROUTE_BY_ID);
        return jdbcTemplate.saveOrUpdate(sqlQuery, params);
    }

    @Override
    public boolean delete(Route entity) {
        final String sqlQuery = SqlProperties.getValue(DELETE_ROUTE_BY_ID);
        return jdbcTemplate.delete(sqlQuery, entity.getId());
    }

    @Override
    public Optional<Route> findById(Long id) {
        final String sqlQuery = SqlProperties.getValue(FIND_ROUTE_BY_ID);
        final RouteMapper routeMapper = new RouteMapper(true);
        routeMapper.mapDepartureArrivalStationRelations(new StationMapper(true));
        return jdbcTemplate.findByQuery(sqlQuery, routeMapper, id);
    }
}

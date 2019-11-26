package com.proky.booking.persistence.dao.mysql;

import com.proky.booking.persistence.dao.ITrainDao;
import com.proky.booking.persistence.entity.Station;
import com.proky.booking.persistence.entity.Train;
import com.proky.booking.persistence.jdbc.JdbcTemplate;
import com.proky.booking.persistence.mapper.*;
import com.proky.booking.util.properties.SqlProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

import static com.proky.booking.util.properties.SqlProperties.*;

public class TrainDaoImpl implements ITrainDao {
    private static TrainDaoImpl instance;
    private JdbcTemplate jdbcTemplate;

    private static final Logger log = LogManager.getLogger(TrainDaoImpl.class);

    private TrainDaoImpl() {
        jdbcTemplate = JdbcTemplate.getInstance();
    }

    public static TrainDaoImpl getInstance() {
        if (instance == null) {
            instance = new TrainDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Train> findTrainsByDateAndTimeAndStation(Date departureDate, Time departureTime, Station station, long pageSize, long offSet) {
        final String sqlQuery = SqlProperties.getValue(FIND_TRAINS_BY_DATE_TIME_STATION);
        final TrainMapper trainMapper = new TrainMapper(true);
        final RouteMapper routeMapper = new RouteMapper(true);
        routeMapper.mapDepartureArrivalStationRelations(new StationMapper(true));
        trainMapper.mapRouteRelation(routeMapper);
        trainMapper.mapTrainTypeRelation(new TrainTypeMapper(true));
        return jdbcTemplate.findAll(sqlQuery, trainMapper, departureDate, departureTime, departureTime, station.getId(), pageSize, offSet);
    }

    public long countTrainsByDateAndTimeAndStation(Date departureDate, Time departureTime, Station station) {
        final String sqlQuery = SqlProperties.getValue(COUNT_TRAINS_BY_DATE_TIME_STATION);
        return jdbcTemplate.countRows(sqlQuery, departureDate, departureTime, departureTime, station.getId());
    }

    @Override
    public Long save(Train entity) {
        Object[] params = {entity.getTrainType().getId(), entity.getRoute().getId()};
        final String query = SqlProperties.getValue(SAVE_TRAIN);
        return jdbcTemplate.saveOrUpdate(query, params);
    }

    @Override
    public Long update(Train entity) {
        Object[] params = {entity.getTrainType().getId(), entity.getRoute().getId(), entity.getId()};
        String sqlQuery = SqlProperties.getValue(UPDATE_TRAIN_BY_ID);
        return jdbcTemplate.saveOrUpdate(sqlQuery, params);
    }

    @Override
    public boolean delete(Train entity) {
        final String sqlQuery = SqlProperties.getValue(DELETE_TRAIN_BY_ID);
        return jdbcTemplate.delete(sqlQuery, entity.getId());
    }

    @Override
    public Optional<Train> findById(Long id) {
        final String sqlQuery = SqlProperties.getValue(FIND_TRAIN_BY_ID);
        final TrainMapper trainMapper = new TrainMapper(true);
        final RouteMapper routeMapper = new RouteMapper(true);
        routeMapper.mapDepartureArrivalStationRelations(new StationMapper(true));
        trainMapper.mapRouteRelation(routeMapper);
        trainMapper.mapTrainTypeRelation(new TrainTypeMapper(true));
        return jdbcTemplate.findByQuery(sqlQuery, trainMapper, id);
    }
}

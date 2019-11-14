package com.proky.booking.persistence.dao.mysql;

import com.proky.booking.persistence.dao.ITrainDao;
import com.proky.booking.persistence.dao.IUserDao;
import com.proky.booking.persistence.entity.Station;
import com.proky.booking.persistence.entity.Train;
import com.proky.booking.persistence.entity.User;
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
        final String sqlQuery = SqlProperties.getQuery(FIND_TRAINS_BY_DATE_TIME_STATION);
        final TrainMapper trainMapper = new TrainMapper(true);
        trainMapper.mapRouteRelation(new RouteMapper(true));
        trainMapper.mapTrainTypeRelation(new TrainTypeMapper(true));

        return jdbcTemplate.findAll(sqlQuery, trainMapper, departureDate, departureTime, departureTime, station.getId(), pageSize, offSet);
    }

    public long countTrainsByDateAndTimeAndStation(Date departureDate, Time departureTime, Station station) {
        final String sqlQuery = SqlProperties.getQuery(COUNT_TRAINS_BY_DATE_TIME_STATION);
        return jdbcTemplate.countRows(sqlQuery, departureDate, departureTime, departureTime, station.getId());
    }

    @Override
    public Optional<Train> findTrainById(Long id) {
        final String sqlQuery = SqlProperties.getQuery(FIND_TRAIN_BY_ID);
        final TrainMapper trainMapper = new TrainMapper(true);
        trainMapper.mapRouteRelation(new RouteMapper(true));
        trainMapper.mapTrainTypeRelation(new TrainTypeMapper(true));

        return jdbcTemplate.findByQuery(sqlQuery, trainMapper, id);
    }

    @Override
    public Long save(Train entity) {
        return null;
    }

    @Override
    public Long update(Train entity) {
        return null;
    }

    @Override
    public boolean delete(Train entity) {
        return false;
    }

    @Override
    public Optional<Train> findById(Long id) {
        return Optional.empty();
    }
}

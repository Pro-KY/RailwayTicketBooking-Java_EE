package com.proky.booking.persistence.dao.mysql;

import com.proky.booking.persistence.dao.IStationDao;
import com.proky.booking.persistence.entity.Station;
import com.proky.booking.persistence.jdbc.JdbcTemplate;
import com.proky.booking.persistence.mapper.StationMapper;
import com.proky.booking.persistence.mapper.UserMapper;
import com.proky.booking.util.properties.SqlProperties;

import java.util.List;
import java.util.Optional;

import static com.proky.booking.util.properties.SqlProperties.*;

public class StationDaoImpl implements IStationDao {
    private JdbcTemplate jdbcTemplate;

    private static StationDaoImpl mInstance;

    private StationDaoImpl() {
        this.jdbcTemplate = JdbcTemplate.getInstance();
    }

    public static StationDaoImpl getInstance() {
        if (mInstance == null) {
            mInstance = new StationDaoImpl();
        }
        return mInstance;
    }

    public List<Station> findAll() {
        final String query = SqlProperties.getValue(FIND_ALL_STATIONS);
        final StationMapper stationMapper = new StationMapper(false);
        return jdbcTemplate.findAll(query, stationMapper);
    }

    @Override
    public Long save(Station entity) {
        Object[] params = {entity.getName()};
        String sqlQuery = SqlProperties.getValue(SAVE_STATION);
        return jdbcTemplate.saveOrUpdate(sqlQuery, params);
    }

    @Override
    public Long update(Station entity) {
        Object[] params = {entity.getName(), entity.getId()};
        String sqlQuery = SqlProperties.getValue(UPDATE_STATION_BY_ID);
        return jdbcTemplate.saveOrUpdate(sqlQuery, params);
    }

    @Override
    public boolean delete(Station entity) {
        final String sqlQuery = SqlProperties.getValue(DELETE_STATION_BY_ID);
        return jdbcTemplate.delete(sqlQuery, entity.getId());
    }

    @Override
    public Optional<Station> findById(Long id) {
        final String sqlQuery = SqlProperties.getValue(FIND_STATION_BY_ID);
        final StationMapper stationMapper = new StationMapper(false);
        return jdbcTemplate.findByQuery(sqlQuery, stationMapper, id);
    }
}

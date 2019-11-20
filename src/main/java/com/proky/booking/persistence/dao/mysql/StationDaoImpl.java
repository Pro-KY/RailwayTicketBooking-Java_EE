package com.proky.booking.persistence.dao.mysql;

import com.proky.booking.persistence.dao.IStationDao;
import com.proky.booking.persistence.entity.Station;
import com.proky.booking.persistence.jdbc.JdbcTemplate;
import com.proky.booking.persistence.mapper.StationMapper;
import com.proky.booking.util.properties.SqlProperties;

import java.util.List;
import java.util.Optional;

import static com.proky.booking.util.properties.SqlProperties.FIND_ALL_STATIONS;

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

    @Override
    public List<Station> findAll() {
        final String query = SqlProperties.getValue(FIND_ALL_STATIONS);
        final StationMapper stationMapper = new StationMapper(false);

        return jdbcTemplate.findAll(query, stationMapper);
    }

    @Override
    public Long save(Station entity) {
        return null;
    }

    @Override
    public Long update(Station entity) {
        return null;
    }

    @Override
    public boolean delete(Station entity) {
        return false;
    }

    @Override
    public Optional<Station> findById(Long id) {
        return Optional.empty();
    }
}

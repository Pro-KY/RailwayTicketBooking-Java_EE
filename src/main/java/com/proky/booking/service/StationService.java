package com.proky.booking.service;

import com.proky.booking.persistence.dao.factory.DaoFactory;
import com.proky.booking.persistence.entity.Station;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class StationService {
    private static final Logger log = LogManager.getLogger(StationService.class);
    private DaoFactory daoFactory;

    StationService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Station> findAllStations() {
        return daoFactory.getStationDao().findAll();
    }
}

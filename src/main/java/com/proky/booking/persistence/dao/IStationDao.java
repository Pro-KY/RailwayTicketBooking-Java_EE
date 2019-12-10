package com.proky.booking.persistence.dao;

import com.proky.booking.persistence.entity.RouteStation;
import com.proky.booking.persistence.entity.Station;

import java.util.List;

public interface IStationDao extends IDao<Station> {

    /**
     * Find all stations from a database.
     *
     * @return List of found stations.
     */
    List<Station> findAll();
}

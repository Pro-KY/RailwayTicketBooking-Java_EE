package com.proky.booking.persistence.dao;

import com.proky.booking.persistence.entity.RouteStation;
import com.proky.booking.persistence.entity.Station;
import com.proky.booking.persistence.entity.Train;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface IRouteStationDao extends IDao<RouteStation> {
    List<RouteStation> findAllByRouteId(Long routeId);
}

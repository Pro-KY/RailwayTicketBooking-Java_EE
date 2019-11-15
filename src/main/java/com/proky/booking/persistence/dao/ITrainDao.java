package com.proky.booking.persistence.dao;

import com.proky.booking.persistence.entity.Station;
import com.proky.booking.persistence.entity.Train;
import com.proky.booking.persistence.entity.User;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

public interface ITrainDao extends IDao<Train> {
    List<Train> findTrainsByDateAndTimeAndStation(Date departureDate, Time departureTime, Station station, long pageSize, long offSet);
    long countTrainsByDateAndTimeAndStation(Date departureDate, Time departureTime, Station station);
    Optional<Train> findTrainById(Long id);
}
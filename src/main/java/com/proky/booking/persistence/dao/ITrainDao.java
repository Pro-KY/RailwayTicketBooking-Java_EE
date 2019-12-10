package com.proky.booking.persistence.dao;

import com.proky.booking.persistence.entity.Station;
import com.proky.booking.persistence.entity.Train;
import com.proky.booking.persistence.entity.User;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

public interface ITrainDao extends IDao<Train> {

    /**
     * Find by departure date, time and end station.
     *
     * @param departureDate  departure date
     * @param departureTime  departure time
     * @param station end station
     * @param pageSize amount of entries to retrieve
     * @param offSet offset of the start search point
     * @return found trains list
     */
    List<Train> findTrainsByDateAndTimeAndStation(Date departureDate, Time departureTime, Station station, long pageSize, long offSet);

    /**
     * Get avaliable trains amount by departure date, time and end station.
     *
     * @param departureDate  departure date
     * @param departureTime  departure time
     * @param station end station
     * @return amount of trains
     */
    long countTrainsByDateAndTimeAndStation(Date departureDate, Time departureTime, Station station);
}

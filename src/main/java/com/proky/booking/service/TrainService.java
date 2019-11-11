package com.proky.booking.service;

import com.proky.booking.dto.PageDto;
import com.proky.booking.dto.StationDto;
import com.proky.booking.dto.TrainDto;
import com.proky.booking.persistence.dao.IRouteStationDao;
import com.proky.booking.persistence.dao.ITrainDao;
import com.proky.booking.persistence.dao.factory.DaoFactory;
import com.proky.booking.persistence.entity.RouteStation;
import com.proky.booking.persistence.entity.Station;
import com.proky.booking.persistence.entity.Train;
import com.proky.booking.util.SqlDateTimeConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.sql.Date;
import java.sql.Time;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TrainService {
    private static final Logger log = LogManager.getLogger(SignInService.class);
    private DaoFactory daoFactory;

    TrainService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public PageDto findTrains(PageDto pageDto, String dateUI, String timeUI, String stationId) {

        final SqlDateTimeConverter instance = SqlDateTimeConverter.getInstance();
        final Time time = instance.convertToSqlTime(timeUI);
        final Date date = instance.convertToSqlDate(dateUI);
        final Station station = new Station(Long.valueOf(stationId));

        final PaginationService paginationService = new PaginationService(pageDto);
        paginationService.calculatePagination();

        final long offSet = paginationService.getOffSet();
        final long pageSize = paginationService.getPageSize();

        final ITrainDao trainDao = daoFactory.getTrainDao();
        final IRouteStationDao routeStationDao = daoFactory.getRouteStationDao();
        final ModelMapper modelMapper = new ModelMapper();

        final List<TrainDto> foundTrains = trainDao.findTrainByDateAndTimeAndStation(date, time, station, pageSize, offSet)
                .stream()
                .map(train -> {
                    final TrainDto trainDto = modelMapper.map(train, TrainDto.class);
                    final List<StationDto> routeStations = routeStationDao.findAllByRouteId(trainDto.getRouteId())
                            .stream().map(routeStation -> modelMapper.map(routeStation, StationDto.class)).collect(Collectors.toList());
                    trainDto.setStations(routeStations);
                    return trainDto;
                }).collect(Collectors.toList());

        foundTrains.forEach(trainDto -> System.out.println(trainDto.toString()));
        pageDto.setPageList(foundTrains);
        paginationService.updatePageDto();

        return pageDto;
    }
}

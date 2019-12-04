package com.proky.booking.service;

import com.proky.booking.persistence.transaction.Transactional;
import com.proky.booking.dto.PageDto;
import com.proky.booking.dto.StationDto;
import com.proky.booking.dto.TrainDto;
import com.proky.booking.exception.ServiceException;
import com.proky.booking.persistence.dao.IRouteStationDao;
import com.proky.booking.persistence.dao.ITrainDao;
import com.proky.booking.persistence.dao.factory.DaoFactory;
import com.proky.booking.persistence.entity.Station;
import com.proky.booking.persistence.entity.Train;
import com.proky.booking.util.ModelMapperWrapper;
import com.proky.booking.util.SqlDateTimeConverter;
import com.proky.booking.util.properties.MessageProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

public class TrainService {
    private static final Logger log = LogManager.getLogger(SignInService.class);
    private DaoFactory daoFactory;

    TrainService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Transactional(readOnly = true)
    public PageDto findTrains(final PageDto pageDto, String dateUI, String timeUI, String stationId) {
        final ITrainDao trainDao = daoFactory.getTrainDao();

        final SqlDateTimeConverter instance = SqlDateTimeConverter.getInstance();
        final Time time = instance.convertToSqlTime(timeUI);
        final Date date = instance.convertToSqlDate(dateUI);
        final Station station = new Station(Long.valueOf(stationId));

        final long foundTrainsAmount = trainDao.countTrainsByDateAndTimeAndStation(date, time, station);

        final PaginationService paginationService = new PaginationService(pageDto);
        paginationService.setAllRowsAmount(foundTrainsAmount);
        paginationService.calculatePagination();

        final long offSet = paginationService.getOffSet();
        final long pageSize = paginationService.getPageSize();

        final List<TrainDto> foundTrains = trainDao.
                findTrainsByDateAndTimeAndStation(date, time, station, pageSize, offSet)
                .stream()
                .map(this::mapTrainToDto)
                .collect(Collectors.toList());

        pageDto.setPageList(foundTrains);
        paginationService.updatePageDto();

        return paginationService.getpageDto();
    }

    public TrainDto findTrainById(Long id) {
        final ITrainDao trainDao = daoFactory.getTrainDao();
        final Train train = trainDao.findById(id).orElseThrow(() -> new ServiceException(MessageProperties.NOT_FOUND_ENTITY));
        final ModelMapper modelMapper = ModelMapperWrapper.getInstance().getModelMapper();
        return modelMapper.map(train, TrainDto.class);
    }

    private TrainDto mapTrainToDto(Train train) {
        final IRouteStationDao routeStationDao = daoFactory.getRouteStationDao();
        final ModelMapper modelMapper = ModelMapperWrapper.getInstance().getModelMapper();
        final TrainDto trainDto = modelMapper.map(train, TrainDto.class);

        final List<StationDto> routeStations = routeStationDao
                .findAllByRouteId(trainDto.getRouteId())
                .stream()
                .map(routeStation -> modelMapper.map(routeStation, StationDto.class))
                .collect(Collectors.toList());

        trainDto.setStations(routeStations);
        return trainDto;
    }
}

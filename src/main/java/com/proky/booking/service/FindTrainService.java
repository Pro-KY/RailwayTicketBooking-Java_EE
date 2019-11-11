package com.proky.booking.service;

import com.proky.booking.dto.PageDto;
import com.proky.booking.persistence.dao.factory.DaoFactory;
import com.proky.booking.persistence.entity.Station;
import com.proky.booking.persistence.entity.Train;
import com.proky.booking.util.SqlDateTimeConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class FindTrainService {
    private static final Logger log = LogManager.getLogger(SignInService.class);
    private DaoFactory daoFactory;

    FindTrainService(DaoFactory daoFactory) {
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

        final List<Train> trainByDateAndTimeAndStation = daoFactory.getTrainDao().findTrainByDateAndTimeAndStation(date, time, station, pageSize, offSet);
        pageDto.setPaginationList(trainByDateAndTimeAndStation);
        paginationService.updatePageDto();

        return pageDto;
    }
}

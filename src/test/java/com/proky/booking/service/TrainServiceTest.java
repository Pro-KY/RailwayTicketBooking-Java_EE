package com.proky.booking.service;

import com.proky.booking.dto.PageDto;
import com.proky.booking.dto.TrainDto;
import com.proky.booking.exception.ServiceException;
import com.proky.booking.persistence.dao.IRouteStationDao;
import com.proky.booking.persistence.dao.ITrainDao;
import com.proky.booking.persistence.dao.factory.DaoFactory;
import com.proky.booking.persistence.entity.Station;
import com.proky.booking.persistence.entity.Train;
import com.proky.booking.stub.PageDtoStubProvider;
import com.proky.booking.stub.TrainDataStubProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TrainServiceTest {

    @Mock
    private DaoFactory daoFactory;

    @Mock
    private ITrainDao trainDao;

    @Mock
    private IRouteStationDao routeStationDao;

    @InjectMocks
    private TrainService trainService;

    private final PageDtoStubProvider pageDtoStubProvider = PageDtoStubProvider.getInstance();
    private final TrainDataStubProvider trainDataStubProvider = TrainDataStubProvider.getInstance();


    @Test
    public void findTrainsWithDefaultPageDtoAndValidParameters() {
        final Long expectedTrainsAmount = 4L;

        final List<Train> trains = trainDataStubProvider.getTrains();
        final List<TrainDto> expectedTrainsDto = trainDataStubProvider.getTrainsDto();

        String departureDateFromRequest = "10/31/2019";
        String departureTimeFromRequest = "9:15 PM";
        String stationIdFromRequest = "1";

        when(daoFactory.getTrainDao()).thenReturn(trainDao);
        when(daoFactory.getRouteStationDao()).thenReturn(routeStationDao);

        when(trainDao.findTrainsByDateAndTimeAndStation(any(Date.class), any(Time.class), any(Station.class), anyLong(), anyLong())).thenReturn(trains);
        when(trainDao.countTrainsByDateAndTimeAndStation(any(Date.class), any(Time.class), any(Station.class))).thenReturn(expectedTrainsAmount);
        when(routeStationDao.findAllByRouteId(anyLong())).thenReturn(new ArrayList<>());

        final PageDto defaultPageDto = pageDtoStubProvider.getDefaultPageDto();
        final PageDto actualTrainsPageDto = trainService.findTrains(defaultPageDto, departureDateFromRequest, departureTimeFromRequest, stationIdFromRequest);

        final PageDto expectedTrainsPageDto = pageDtoStubProvider.getFirstPageDto();
        expectedTrainsPageDto.setPageList(expectedTrainsDto);

        assertEquals(expectedTrainsPageDto, actualTrainsPageDto);

        verify(trainDao, times(1)).findTrainsByDateAndTimeAndStation(any(Date.class), any(Time.class), any(Station.class), anyLong(), anyLong());
        verify(trainDao, times(1)).countTrainsByDateAndTimeAndStation(any(Date.class), any(Time.class), any(Station.class));
        verify(routeStationDao, times(3)).findAllByRouteId(anyLong());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void findTrainsWithDefaultPageDtoAndIncorrectDateTimeParameters() {
        final Long expectedTrainsAmount = 4L;

        final List<Train> trains = trainDataStubProvider.getTrains();
        final List<TrainDto> expectedTrainsDto = trainDataStubProvider.getTrainsDto();

        String incorrectDepartureDateFromRequest = "1031/2019";
        String incorrectDepartureTimeFromRequest = "915PM";
        String stationIdFromRequest = "1";

        when(daoFactory.getTrainDao()).thenReturn(trainDao);
        when(daoFactory.getRouteStationDao()).thenReturn(routeStationDao);

        when(trainDao.findTrainsByDateAndTimeAndStation(any(Date.class), any(Time.class), any(Station.class), anyLong(), anyLong())).thenReturn(trains);
        when(trainDao.countTrainsByDateAndTimeAndStation(any(Date.class), any(Time.class), any(Station.class))).thenReturn(expectedTrainsAmount);
        when(routeStationDao.findAllByRouteId(anyLong())).thenReturn(new ArrayList<>());

        final PageDto defaultPageDto = pageDtoStubProvider.getDefaultPageDto();
        trainService.findTrains(defaultPageDto, incorrectDepartureDateFromRequest, incorrectDepartureTimeFromRequest, stationIdFromRequest);

        final PageDto expectedTrainsPageDto = pageDtoStubProvider.getFirstPageDto();
        expectedTrainsPageDto.setPageList(expectedTrainsDto);
    }

    @Test
    public void findTrainByIdWithCorrectId() {
        Long correctId = 1L;
        when(daoFactory.getTrainDao()).thenReturn(trainDao);
        final TrainDto expectedTrainDto = trainDataStubProvider.getTrainDtoStub();
        final Optional<Train> optionalTrain = Optional.of(trainDataStubProvider.getTrainStub());
        when(trainDao.findById(correctId)).thenReturn(optionalTrain);

        final TrainDto actualTrainDto = trainService.findTrainById(correctId);
        assertEquals(expectedTrainDto, actualTrainDto);
        verify(trainDao, times(1)).findById(anyLong());
    }

    @Test(expected = ServiceException.class)
    public void findTrainByIdWithIncorrectId() {
        Long incorrectId = null;
        when(daoFactory.getTrainDao()).thenReturn(trainDao);
        when(trainDao.findById(incorrectId)).thenReturn(Optional.empty());
        trainService.findTrainById(incorrectId);
    }
}

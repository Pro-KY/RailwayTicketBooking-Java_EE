package com.proky.booking.service;

import com.proky.booking.persistence.dao.IStationDao;
import com.proky.booking.persistence.dao.factory.DaoFactory;
import com.proky.booking.persistence.entity.Station;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StationServiceTest {
    @Mock
    private DaoFactory daoFactory;
    @Mock
    private IStationDao stationDao;

    @InjectMocks
    private StationService stationService;

    @Test
    public void findAllStationsTest() {
        List<Station> stations = new ArrayList<>();
        stations.add(new Station(1L, "Киев"));
        stations.add(new Station(2L, "Фастов"));
        stations.add(new Station(3L, "Житомир"));

        when(daoFactory.getStationDao()).thenReturn(stationDao);
        when(stationDao.findAll()).thenReturn(stations);

        int expectedSize = 3;
        int actualSize = stationService.findAllStations().size();

        assertEquals(expectedSize, actualSize);
        verify(stationDao, times(1)).findAll();
    }
}

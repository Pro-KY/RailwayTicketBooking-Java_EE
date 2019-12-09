package com.proky.booking.stub;

import com.proky.booking.dto.TrainDto;
import com.proky.booking.persistence.entity.*;
import com.proky.booking.util.ModelMapperWrapper;
import com.proky.booking.util.PasswordEncryptor;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TrainDataStubProvider {

    private static TrainDataStubProvider mInstance;
    private final PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();
    private final ModelMapper modelMapper = ModelMapperWrapper.getInstance().getModelMapper();

    private TrainDataStubProvider() {
    }

    public static TrainDataStubProvider getInstance() {
        if (mInstance == null) {
            mInstance = new TrainDataStubProvider();
        }
        return mInstance;
    }

    public TrainDto getTrainDtoStub() {
        return new TrainDto.Builder().trainType("Люкс")
                .trainSeatPrice(new BigDecimal(100.00)).routeId(2L)
                .departureStationId(9L).arrivalStationId(1L)
                .routeDepartureDate(Date.valueOf("2019-01-10")).routeArrivalDate(Date.valueOf("2019-11-01"))
                .routeDepartureTime(Time.valueOf("21:15:00")).routeArrivalTime(Time.valueOf("05:58:00"))
                .routeLengthFactor(0.7).trainId(1).build();
    }

    public Train getTrainStub() {
        final TrainType luxeTrainType = new TrainType(1L, "Люкс", new BigDecimal(100.00));
        final Route route = new Route(2L, new Station(9L), new Station(1L), Date.valueOf("2019-01-10"), Date.valueOf("2019-11-01"),
                Time.valueOf("21:15:00"), Time.valueOf("05:58:00"), 0.7);

        return new Train(1L, luxeTrainType, route);
    }

    public List<TrainDto> getTrainsDto() {
        final List<TrainDto> trainDtoList = new ArrayList<>();

        final TrainDto firstTrainDto = new TrainDto.Builder().trainType("Люкс")
                .trainSeatPrice(new BigDecimal(100.00)).routeId(2L)
                .departureStationId(9L).arrivalStationId(1L)
                .routeDepartureDate(Date.valueOf("2019-01-10")).routeArrivalDate(Date.valueOf("2019-11-01"))
                .routeDepartureTime(Time.valueOf("21:15:00")).routeArrivalTime(Time.valueOf("05:58:00"))
                .routeLengthFactor(0.7).trainId(1).build();

        final TrainDto secondTrainDto = new TrainDto.Builder().trainType("Эконом")
                .trainSeatPrice(new BigDecimal(30.00)).routeId(3L)
                .departureStationId(15L).arrivalStationId(1L)
                .routeDepartureDate(Date.valueOf("2019-01-10")).routeArrivalDate(Date.valueOf("2019-11-01"))
                .routeDepartureTime(Time.valueOf("17:40:00")).routeArrivalTime(Time.valueOf("03:38:00"))
                .routeLengthFactor(0.6).trainId(3).build();

        final TrainDto thirdTrainDto = new TrainDto.Builder().trainType("Люкс")
                .trainSeatPrice(new BigDecimal(100.00)).routeId(6L)
                .departureStationId(16L).arrivalStationId(1L)
                .routeDepartureDate(Date.valueOf("2019-01-10")).routeArrivalDate(Date.valueOf("2019-11-01"))
                .routeDepartureTime(Time.valueOf("20:15:00")).routeArrivalTime(Time.valueOf("04:58:00"))
                .routeLengthFactor(0.5).trainId(1).build();

        trainDtoList.add(firstTrainDto);
        trainDtoList.add(secondTrainDto);
        trainDtoList.add(thirdTrainDto);
        return trainDtoList;
    }


    public List<Train> getTrains() {
        final List<Train> trainsList = new ArrayList<>();

        final TrainType luxeTrainType = new TrainType(1L, "Люкс", new BigDecimal(100.00));
        final TrainType economTrainType = new TrainType(1L, "Эконом", new BigDecimal(30.00));

        final Route firstRoute = new Route(2L, new Station(9L), new Station(1L), Date.valueOf("2019-01-10"), Date.valueOf("2019-11-01"),
                Time.valueOf("21:15:00"), Time.valueOf("05:58:00"), 0.7);

        final Route secondRoute = new Route(3L, new Station(15L), new Station(1L), Date.valueOf("2019-01-10"), Date.valueOf("2019-11-01"),
                Time.valueOf("17:40:00"), Time.valueOf("03:38:00"), 0.6);

        final Route thirdRoute = new Route(6L, new Station(16L), new Station(1L), Date.valueOf("2019-01-10"), Date.valueOf("2019-11-01"),
                Time.valueOf("20:15:00"), Time.valueOf("04:58:00"), 0.5);

        trainsList.add(new Train(1L, luxeTrainType, firstRoute));
        trainsList.add(new Train(3L, economTrainType, secondRoute));
        trainsList.add(new Train(1L, luxeTrainType, thirdRoute));
        return trainsList;

    }

}


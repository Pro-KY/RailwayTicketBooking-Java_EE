package com.proky.booking.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

public class TrainDto implements Serializable {
    private Integer trainId;
    private String trainType;
    private String trainSeatPrice;

    private Long routeId;
    private String routeName;
    private Date routeDepartureDate;
    private Date routeArrivalDate;
    private Time routeDepartureTime;
    private Time routeArrivalTime;
    private Double routeLengthFactor;

    private List<StationDto> stations;

    public TrainDto() {}

    public List<StationDto> getStations() {
        return stations;
    }

    public void setStations(List<StationDto> stations) {
        this.stations = stations;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getTrainSeatPrice() {
        return trainSeatPrice;
    }

    public void setTrainSeatPrice(String trainSeatPrice) {
        this.trainSeatPrice = trainSeatPrice;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Date getRouteDepartureDate() {
        return routeDepartureDate;
    }

    public void setRouteDepartureDate(Date routeDepartureDate) {
        this.routeDepartureDate = routeDepartureDate;
    }

    public Date getRouteArrivalDate() {
        return routeArrivalDate;
    }

    public void setRouteArrivalDate(Date routeArrivalDate) {
        this.routeArrivalDate = routeArrivalDate;
    }

    public Time getRouteDepartureTime() {
        return routeDepartureTime;
    }

    public void setRouteDepartureTime(Time routeDepartureTime) {
        this.routeDepartureTime = routeDepartureTime;
    }

    public Time getRouteArrivalTime() {
        return routeArrivalTime;
    }

    public void setRouteArrivalTime(Time routeArrivalTime) {
        this.routeArrivalTime = routeArrivalTime;
    }

    public Double getRouteLengthFactor() {
        return routeLengthFactor;
    }

    public void setRouteLengthFactor(Double routeLengthFactor) {
        this.routeLengthFactor = routeLengthFactor;
    }


    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    @Override
    public String toString() {
        return "TrainDto{" +
                "trainType='" + trainType + '\'' +
                ", trainSeatPrice='" + trainSeatPrice + '\'' +
                ", routeId=" + routeId +
                ", routeName='" + routeName + '\'' +
                ", departureDate='" + routeDepartureDate + '\'' +
                ", arrivalDate='" + routeArrivalDate + '\'' +
                ", departureTime='" + routeDepartureTime + '\'' +
                ", arrivalTime='" + routeArrivalTime + '\'' +
                ", routeLengthFactor=" + routeLengthFactor +
                ", stations=" + stations +
                ", trainID=" + trainId +
                '}';
    }
}

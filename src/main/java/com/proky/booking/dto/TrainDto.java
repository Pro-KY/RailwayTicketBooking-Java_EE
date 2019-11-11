package com.proky.booking.dto;

import java.util.Map;

public class TrainDto {
    String trainName;
    Integer routeId;
    String routeName;
    String departureDate;
    String arrivalDate;
    String departureTime;
    String arrivalTime;
    private Map<String, String> stationsMap;

    public TrainDto(Map<String, String> stationsMap) {
        this.stationsMap = stationsMap;
    }

    public Map<String, String> getStationsMap() {
        return stationsMap;
    }

    public void setStationsMap(Map<String, String> stationsMap) {
        this.stationsMap = stationsMap;
    }
}

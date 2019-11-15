package com.proky.booking.dto;


import java.io.Serializable;

public class StationDto implements Serializable {
    private Long stationId;
    private String stationName;

    public StationDto() { }

    public StationDto(Long stationId, String stationName) {
        this.stationId = stationId;
        this.stationName = stationName;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

//    @Override
//    public String toString() {
//       return stationName;
//    }
}

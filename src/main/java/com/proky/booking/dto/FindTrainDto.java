package com.proky.booking.dto;

import java.util.Map;

public class FindTrainDto {
    private Map<String, String> stationsMap;

    public FindTrainDto(Map<String, String> stationsMap) {
        this.stationsMap = stationsMap;
    }

    public Map<String, String> getStationsMap() {
        return stationsMap;
    }

    public void setStationsMap(Map<String, String> stationsMap) {
        this.stationsMap = stationsMap;
    }
}

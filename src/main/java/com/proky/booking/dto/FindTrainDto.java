package com.proky.booking.dto;

import java.util.Map;

public class FindTrainDto {
    Map<String, String> stringStringMap;

    public FindTrainDto(Map<String, String> stringStringMap) {
        this.stringStringMap = stringStringMap;
    }

    public Map<String, String> getStringStringMap() {
        return stringStringMap;
    }

    public void setStringStringMap(Map<String, String> stringStringMap) {
        this.stringStringMap = stringStringMap;
    }
}

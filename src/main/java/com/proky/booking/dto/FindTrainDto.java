package com.proky.booking.dto;

import com.proky.booking.validation.annotation.Length;

public class FindTrainDto {
    @Length(min = 1, max = Integer.MAX_VALUE)
    private String goingToId;

    @Length(min = 10, max = 12)
    private String departureDate;

    @Length(min = 7, max = 8)
    private String departureTime;

    public FindTrainDto() {}

    public FindTrainDto(String goingToId, String departureDate, String departureTime) {
        this.goingToId = goingToId;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
    }

    public String getGoingToId() {
        return goingToId;
    }

    public void setGoingToId(String goingToId) {
        this.goingToId = goingToId;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "FindTrainDto{" +
                "goingToId='" + goingToId + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", departureTime='" + departureTime + '\'' +
                '}';
    }
}

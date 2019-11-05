package com.proky.booking.persistence.entity;

import java.sql.Date;
import java.sql.Time;

public class Route extends Entity<Long> {

    private String name;
    private Date departureDate;
    private Date arrivalDate;
    private Time departureTime;
    private Time arrivalTime;
    private Double routeLengthFactor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.sql.Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(java.sql.Date departureDate) {
        this.departureDate = departureDate;
    }


    public java.sql.Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(java.sql.Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }


    public java.sql.Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(java.sql.Time departureTime) {
        this.departureTime = departureTime;
    }


    public java.sql.Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(java.sql.Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }


    public double getRouteLengthFactor() {
        return routeLengthFactor;
    }

    public void setRouteLengthFactor(double routeLengthFactor) {
        this.routeLengthFactor = routeLengthFactor;
    }

}

package com.proky.booking.persistence.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Route extends Entity<Long> {
    private String name;
    private Date departureDate;
    private Date arrivalDate;
    private Time departureTime;
    private Time arrivalTime;
    private Double routeLengthFactor;

    public Route(Long id, String name, Date departureDate, Date arrivalDate, Time departureTime, Time arrivalTime, Double routeLengthFactor) {
        super(id);
        this.name = name;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.routeLengthFactor = routeLengthFactor;
    }

    public Route(Long id) {
        super(id);
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(this.getId(), route.getId()) &&
                Objects.equals(name, route.name) &&
                Objects.equals(departureDate, route.departureDate) &&
                Objects.equals(arrivalDate, route.arrivalDate) &&
                Objects.equals(departureTime, route.departureTime) &&
                Objects.equals(arrivalTime, route.arrivalTime) &&
                Objects.equals(routeLengthFactor, route.routeLengthFactor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), name, departureDate, arrivalDate, departureTime, arrivalTime, routeLengthFactor);
    }
}

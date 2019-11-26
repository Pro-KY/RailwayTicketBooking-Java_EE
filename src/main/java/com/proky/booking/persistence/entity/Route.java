package com.proky.booking.persistence.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Route extends Entity<Long> {
    private Station departureStation;
    private Station arrivalStation;
    private Date departureDate;
    private Date arrivalDate;
    private Time departureTime;
    private Time arrivalTime;
    private Double routeLengthFactor;

    public Route(Long id, Station departureStation, Station arrivalStation, Date departureDate, Date arrivalDate, Time departureTime, Time arrivalTime, Double routeLengthFactor) {
        super(id);
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.routeLengthFactor = routeLengthFactor;
    }

    public Route(Long id) {
        super(id);
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

    public Station getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(Station departureStation) {
        this.departureStation = departureStation;
    }

    public Station getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(Station arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(departureStation, route.departureStation) &&
                Objects.equals(arrivalStation, route.arrivalStation) &&
                Objects.equals(departureDate, route.departureDate) &&
                Objects.equals(arrivalDate, route.arrivalDate) &&
                Objects.equals(departureTime, route.departureTime) &&
                Objects.equals(arrivalTime, route.arrivalTime) &&
                Objects.equals(routeLengthFactor, route.routeLengthFactor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departureStation, arrivalStation, departureDate, arrivalDate, departureTime, arrivalTime, routeLengthFactor);
    }

    @Override
    public String toString() {
        return "Route{" +
                "departureStation=" + departureStation +
                ", arrivalStation=" + arrivalStation +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", routeLengthFactor=" + routeLengthFactor +
                ", id=" + id +
                '}';
    }
}

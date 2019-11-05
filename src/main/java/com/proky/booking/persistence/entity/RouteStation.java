package com.proky.booking.persistence.entity;

public class RouteStation extends Entity<Long> {
    private Route route;
    private Station station;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}

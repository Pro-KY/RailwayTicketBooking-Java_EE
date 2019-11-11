package com.proky.booking.persistence.entity;

public class RouteStation extends Entity<Long> {
    private Route route;
    private Station station;

    public RouteStation(Long id, Route route, Station station) {
        super(id);
        this.route = route;
        this.station = station;
    }

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

package com.proky.booking.persistence.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteStation that = (RouteStation) o;
        return  Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(route, that.route) &&
                Objects.equals(station, that.station);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), route, station);
    }
}

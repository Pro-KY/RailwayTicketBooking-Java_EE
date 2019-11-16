package com.proky.booking.persistence.entity;

import java.util.Objects;

public class Train extends Entity<Long> {
    private TrainType trainType;
    private Route route;

    public Train(Long id, TrainType trainType, Route route) {
        super(id);
        this.trainType = trainType;
        this.route = route;
    }

    public TrainType getTrainType() {
        return trainType;
    }

    public void setTrainType(TrainType trainType) {
        this.trainType = trainType;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;

        return  Objects.equals(this.getId(), train.getId()) &&
                Objects.equals(trainType, train.trainType) &&
                Objects.equals(route, train.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), trainType, route);
    }
}

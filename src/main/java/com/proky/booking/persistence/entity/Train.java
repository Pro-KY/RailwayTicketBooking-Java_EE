package com.proky.booking.persistence.entity;

public class Train extends Entity<Long> {
    private TrainType trainType;
    private Route route;

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
}
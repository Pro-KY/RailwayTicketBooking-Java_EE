package com.proky.booking.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class InvoiceDto implements Serializable {
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private Long trainId;
    private String trainType;
    private String routeName;
    private Date routeDepartureDate;
    private Date routeArrivalDate;
    private Time routeDepartureTime;
    private Time routeArrivalTime;
    private BigDecimal seatsAmount;
    private BigDecimal sum;

    public InvoiceDto() {}

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Date getRouteDepartureDate() {
        return routeDepartureDate;
    }

    public void setRouteDepartureDate(Date routeDepartureDate) {
        this.routeDepartureDate = routeDepartureDate;
    }

    public Date getRouteArrivalDate() {
        return routeArrivalDate;
    }

    public void setRouteArrivalDate(Date routeArrivalDate) {
        this.routeArrivalDate = routeArrivalDate;
    }

    public Time getRouteDepartureTime() {
        return routeDepartureTime;
    }

    public void setRouteDepartureTime(Time routeDepartureTime) {
        this.routeDepartureTime = routeDepartureTime;
    }

    public Time getRouteArrivalTime() {
        return routeArrivalTime;
    }

    public void setRouteArrivalTime(Time routeArrivalTime) {
        this.routeArrivalTime = routeArrivalTime;
    }

    public BigDecimal getSeatsAmount() {
        return seatsAmount;
    }

    public void setSeatsAmount(BigDecimal seatsAmount) {
        this.seatsAmount = seatsAmount;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "InvoiceDto{" +
                "userId='" + userId + '\'' +
                "userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", trainId=" + trainId +
                ", trainType='" + trainType + '\'' +
                ", routeName='" + routeName + '\'' +
                ", routeDepartureDate=" + routeDepartureDate +
                ", routeArrivalDate=" + routeArrivalDate +
                ", routeDepartureTime=" + routeDepartureTime +
                ", routeArrivalTime=" + routeArrivalTime +
                ", seatsAmount=" + seatsAmount +
                ", sum=" + sum +
                '}';
    }
}

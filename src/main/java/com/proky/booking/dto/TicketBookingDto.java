package com.proky.booking.dto;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class TicketBookingDto implements Serializable {
    private String trainId;
    private String firstName;
    private String lastName;
    private String seats;

    public TicketBookingDto() {}

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "TicketBookingDto{" +
                "trainId=" + trainId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", seats=" + seats +
                '}';
    }
}

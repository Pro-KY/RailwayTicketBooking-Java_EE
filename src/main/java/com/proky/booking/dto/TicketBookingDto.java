package com.proky.booking.dto;

import com.proky.booking.validation.annotation.Length;
import com.proky.booking.validation.annotation.Size;

import java.io.Serializable;

public class TicketBookingDto implements Serializable {
    private String trainId;

    @Length(min = 3, max = 20)
    private String firstName;

    @Length(min = 3, max = 20)
    private String lastName;

    @Size(max = 3)
    private String seatsAmount;

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

    public String getSeatsAmount() {
        return seatsAmount;
    }

    public void setSeatsAmount(String seatsAmount) {
        this.seatsAmount = seatsAmount;
    }

    @Override
    public String toString() {
        return "TicketBookingDto{" +
                "trainId=" + trainId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", seats=" + seatsAmount +
                '}';
    }
}

package com.proky.booking.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

public class TrainDto implements Serializable {
    private Integer trainId;
    private String trainType;
    private BigDecimal trainSeatPrice;

    private Long departureStationId;
    private Long arrivalStationId;

    private Long routeId;
    private Date routeDepartureDate;
    private Date routeArrivalDate;
    private Time routeDepartureTime;
    private Time routeArrivalTime;
    private Double routeLengthFactor;

    private List<StationDto> stations;

    public TrainDto() {}

    private TrainDto(Builder builder) {
        setTrainId(builder.trainId);
        setTrainType(builder.trainType);
        setTrainSeatPrice(builder.trainSeatPrice);
        setDepartureStationId(builder.departureStationId);
        setArrivalStationId(builder.arrivalStationId);
        setRouteId(builder.routeId);
        setRouteDepartureDate(builder.routeDepartureDate);
        setRouteArrivalDate(builder.routeArrivalDate);
        setRouteDepartureTime(builder.routeDepartureTime);
        setRouteArrivalTime(builder.routeArrivalTime);
        setRouteLengthFactor(builder.routeLengthFactor);
        setStations(builder.stations);
    }


    public Long getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(Long departureStationId) {
        this.departureStationId = departureStationId;
    }

    public Long getArrivalStationId() {
        return arrivalStationId;
    }

    public void setArrivalStationId(Long arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    public List<StationDto> getStations() {
        return stations;
    }

    public void setStations(List<StationDto> stations) {
        this.stations = stations;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public BigDecimal getTrainSeatPrice() {
        return trainSeatPrice;
    }

    public void setTrainSeatPrice(BigDecimal trainSeatPrice) {
        this.trainSeatPrice = trainSeatPrice;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
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

    public Double getRouteLengthFactor() {
        return routeLengthFactor;
    }

    public void setRouteLengthFactor(Double routeLengthFactor) {
        this.routeLengthFactor = routeLengthFactor;
    }


    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public static final class Builder {
        private Integer trainId;
        private String trainType;
        private BigDecimal trainSeatPrice;
        private Long departureStationId;
        private Long arrivalStationId;
        private Long routeId;
        private Date routeDepartureDate;
        private Date routeArrivalDate;
        private Time routeDepartureTime;
        private Time routeArrivalTime;
        private Double routeLengthFactor;
        private List<StationDto> stations;

        public Builder() {
        }

        public Builder trainId(Integer val) {
            trainId = val;
            return this;
        }

        public Builder trainType(String val) {
            trainType = val;
            return this;
        }

        public Builder trainSeatPrice(BigDecimal val) {
            trainSeatPrice = val;
            return this;
        }

        public Builder departureStationId(Long val) {
            departureStationId = val;
            return this;
        }

        public Builder arrivalStationId(Long val) {
            arrivalStationId = val;
            return this;
        }

        public Builder routeId(Long val) {
            routeId = val;
            return this;
        }

        public Builder routeDepartureDate(Date val) {
            routeDepartureDate = val;
            return this;
        }

        public Builder routeArrivalDate(Date val) {
            routeArrivalDate = val;
            return this;
        }

        public Builder routeDepartureTime(Time val) {
            routeDepartureTime = val;
            return this;
        }

        public Builder routeArrivalTime(Time val) {
            routeArrivalTime = val;
            return this;
        }

        public Builder routeLengthFactor(Double val) {
            routeLengthFactor = val;
            return this;
        }

        public Builder stations(List<StationDto> val) {
            stations = val;
            return this;
        }

        public TrainDto build() {
            return new TrainDto(this);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainDto trainDto = (TrainDto) o;
        return Objects.equals(trainId, trainDto.trainId) &&
                Objects.equals(trainType, trainDto.trainType) &&
                Objects.equals(trainSeatPrice, trainDto.trainSeatPrice) &&
                Objects.equals(departureStationId, trainDto.departureStationId) &&
                Objects.equals(arrivalStationId, trainDto.arrivalStationId) &&
                Objects.equals(routeId, trainDto.routeId) &&
                Objects.equals(routeDepartureDate, trainDto.routeDepartureDate) &&
                Objects.equals(routeArrivalDate, trainDto.routeArrivalDate) &&
                Objects.equals(routeDepartureTime, trainDto.routeDepartureTime) &&
                Objects.equals(routeArrivalTime, trainDto.routeArrivalTime) &&
                Objects.equals(routeLengthFactor, trainDto.routeLengthFactor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainId, trainType, trainSeatPrice, departureStationId, arrivalStationId, routeId, routeDepartureDate, routeArrivalDate, routeDepartureTime, routeArrivalTime, routeLengthFactor);
    }

    @Override
    public String toString() {
        return "TrainDto{" +
                "trainType='" + trainType + '\'' +
                ", trainSeatPrice='" + trainSeatPrice + '\'' +
                ", routeId=" + routeId +
                ", departureStationName=" + departureStationId +
                ", arrivalStationName=" + arrivalStationId +
                ", departureDate='" + routeDepartureDate + '\'' +
                ", arrivalDate='" + routeArrivalDate + '\'' +
                ", departureTime='" + routeDepartureTime + '\'' +
                ", arrivalTime='" + routeArrivalTime + '\'' +
                ", routeLengthFactor=" + routeLengthFactor +
                ", trainID=" + trainId +
                '}';
    }
}

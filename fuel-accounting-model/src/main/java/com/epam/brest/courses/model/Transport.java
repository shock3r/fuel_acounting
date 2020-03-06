package com.epam.brest.courses.model;

import java.util.Date;

public class Transport {
    private Integer transportId;
    private String transportName;
    private Integer fuelId;
    private Double fuelTankCapasity;
    private Date date;

    public Integer getTransportId() {
        return transportId;
    }

    public Transport setTransportId(Integer transportId) {
        this.transportId = transportId;
        return this;
    }

    public String getTransportName() {
        return transportName;
    }

    public Transport setTransportName(String transportName) {
        this.transportName = transportName;
        return this;
    }

    public Integer getFuelId() {
        return fuelId;
    }

    public Transport setFuelId(Integer fuelId) {
        this.fuelId = fuelId;
        return this;
    }

    public Double getFuelTankCapasity() {
        return fuelTankCapasity;
    }

    public Transport setFuelTankCapasity(Double fuelTankCapasity) {
        this.fuelTankCapasity = fuelTankCapasity;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Transport setDate(Date date) {
        this.date = date;
        return this;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "transportId=" + transportId +
                ", transportName='" + transportName + '\'' +
                ", fuelId=" + fuelId +
                ", fuelTankCapasity=" + fuelTankCapasity +
                ", date=" + date +
                '}';
    }

}

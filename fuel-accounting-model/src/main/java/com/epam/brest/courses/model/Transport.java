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

    public void setTransportId(Integer transportId) {
        this.transportId = transportId;
    }

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public Integer getFuelId() {
        return fuelId;
    }

    public void setFuelId(Integer fuelId) {
        this.fuelId = fuelId;
    }

    public Double getFuelTankCapasity() {
        return fuelTankCapasity;
    }

    public void setFuelTankCapasity(Double fuelTankCapasity) {
        this.fuelTankCapasity = fuelTankCapasity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

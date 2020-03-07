package com.epam.brest.courses.model;

import java.util.Date;

public class Transport {
    private Integer transportId;
    private String transportName;
    private Integer fuelId;
    private Double transportTankCapasity;
    private Date transportDate;

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

    public Double getTransportTankCapasity() {
        return transportTankCapasity;
    }

    public Transport setTransportTankCapasity(Double transportTankCapasity) {
        this.transportTankCapasity = transportTankCapasity;
        return this;
    }

    public Date getTransportDate() {
        return transportDate;
    }

    public Transport setTransportDate(Date transportDate) {
        this.transportDate = transportDate;
        return this;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "transportId=" + transportId +
                ", transportName='" + transportName + '\'' +
                ", fuelId=" + fuelId +
                ", transportFuelTankCapasity=" + transportTankCapasity +
                ", transportDate=" + transportDate +
                '}';
    }
}

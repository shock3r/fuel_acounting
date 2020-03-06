package com.epam.brest.courses.model;

public class Fuel {
    private Integer fuelId;
    private String fuelName;

    public Integer getFuelId() {
        return fuelId;
    }

    public Fuel setFuelId(Integer fuelId) {
        this.fuelId = fuelId;
        return this;
    }

    public String getFuelName() {
        return fuelName;
    }

    public Fuel setFuelName(String fuelName) {
        this.fuelName = fuelName;
        return this;
    }

    @Override
    public String toString() {
        return "Fuel{" +
                "fuelId=" + fuelId +
                ", fuelName='" + fuelName + '\'' +
                '}';
    }
}

package com.epam.brest.courses.model;

public class Fuel {
    private Integer fuelId;
    private String fuelName;

    public Integer getFuelId() {
        return fuelId;
    }

    public void setFuelId(Integer fuelId) {
        this.fuelId = fuelId;
    }

    public String getFuelName() {
        return fuelName;
    }

    public void setFuelName(String fuelName) {
        this.fuelName = fuelName;
    }

    @Override
    public String toString() {
        return "Fuel{" +
                "fuelId=" + fuelId +
                ", fuelName='" + fuelName + '\'' +
                '}';
    }
}

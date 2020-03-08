package com.epam.brest.courses.model.dto;

import com.epam.brest.courses.model.Fuel;

/**
 * POJO Fuel for model.
 */
public class FuelDto {

    /**
     * Fuel Id.
     */
    private Integer fuelId;

    /**
     * Fuel Name.
     */
    private String fuelName;

    /**
     * Fuel Sum,
     */
    private Double sumFuel;

    /**
     * Constructor without arguments.
     */
    public FuelDto() {
    }

    /**
     * Constructor with fuel name.
     * @param fuelName
     */
    public FuelDto(String fuelName) {
        this.fuelName = fuelName;
    }

    /**
     * Returns <code>Integer</code> representation of this fuelId.
     * @return fuelId Fuel Id.
     */
    public Integer getFuelId() {
        return fuelId;
    }

    /**
     * Set the fuel's identifier.
     * @param fuelId Fuel Id.
     * @return this FuelDto.
     */
    public FuelDto setFuelId(Integer fuelId) {
        this.fuelId = fuelId;
        return this;
    }

    /**
     * Returns <code>String</code> representation of this fuelName.
     * @return fuelName Fuel Name.
     */
    public String getFuelName() {
        return fuelName;
    }

    /**
     * Set the fuel's name.
     * @param fuelName Fuel Name.
     * @return this FuelDto.
     */
    public FuelDto setFuelName(String fuelName) {
        this.fuelName = fuelName;
        return this;
    }

    /**
     * Returns <code>Double</code> representation of summary fuel.
     * @return sumFuel summary fuel.
     */
    public Double getSumFuel() {
        return sumFuel;
    }

    /**
     * Set the fuel's summary fuel.
     * @param sumFuel Summary fuel.
     * @return this FuelDto.
     */
    public FuelDto setSumFuel(Double sumFuel) {
        this.sumFuel = sumFuel;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "FuelDto{" +
                "fuelId=" + fuelId +
                ", fuelName='" + fuelName + '\'' +
                ", sumFuel=" + sumFuel +
                '}';
    }
}

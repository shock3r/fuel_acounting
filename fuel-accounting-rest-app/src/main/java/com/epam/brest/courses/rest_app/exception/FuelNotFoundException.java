package com.epam.brest.courses.rest_app.exception;

/**
 * Fuel not found exception
 */
public class FuelNotFoundException extends RuntimeException {
    public FuelNotFoundException(Integer fuelId){
        super("Fuel not found for id: " + fuelId);
    }
}

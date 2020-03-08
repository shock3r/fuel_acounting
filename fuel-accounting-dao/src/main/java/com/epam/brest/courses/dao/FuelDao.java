package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Fuel;

import java.util.List;
import java.util.Optional;

/**
 * FUEL DAO JDBC interface.
 */
public interface FuelDao {

    /**
     * Get all fuels.
     *
     * @return list of all fuels.
     */
    List<Fuel> findAll();

    /**
     * Get all fuels wirh specified id.
     *
     * @param fuelId fuel Id.
     * @return fuel by id.
     */
    Optional<Fuel> findById(Integer fuelId);

    /**
     * Persist new fuel.
     *
     * @param fuel fuel.
     * @return persisted fuel id.
     */
    Integer create(Fuel fuel);

    /**
     * update fuel.
     *
     * @param fuel fuel.
     * @return number of updated records in database.
     */
    int update(Fuel fuel);

    /**
     * delete fuel.
     *
     * @param fuelId fuel Id.
     * @return number of deleted records in database.
     */
    int delete(Integer fuelId);

}

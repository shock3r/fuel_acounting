package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Fuel;

import java.util.List;
import java.util.Optional;

public interface FuelDao {
    /**
     * Find all fuels.
     *
     * @return fuels list.
     */
    List<Fuel> findAll();

    /**
     * Find fuels by Id.
     *
     * @param fuelId fuel Id.
     * @return fuel
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
     * @return number of deleted records.
     */
    int delete(Integer fuelId);

}

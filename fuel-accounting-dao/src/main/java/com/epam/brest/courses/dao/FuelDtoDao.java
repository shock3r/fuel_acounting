package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.dto.FuelDto;

import java.util.List;

/**
 * FuelDTO DAO Interface
 */
public interface FuelDtoDao {
    /**
     * Find all fuels with sum of transports tank capasity by fuel type.
     * @return fuels DTO list
     */
    List<FuelDto> findAllWithFuelSum();
}

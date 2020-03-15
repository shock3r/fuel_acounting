package com.epam.brest.courses.service;
import com.epam.brest.courses.model.dto.FuelDto;
import java.util.List;

/**
 * Fuel DTO Service interface.
 */
public interface FuelDtoService {

    /**
     * Find all fuels with sum of transports tank capasity by fuel type.
     *
     * @return Fuels DTO list,
     */
    List<FuelDto> findAllWithFuelSum();

}

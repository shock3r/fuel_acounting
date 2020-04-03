package com.epam.brest.courses.rest_app;

import com.epam.brest.courses.model.dto.FuelDto;
import com.epam.brest.courses.service.FuelDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Fuel DTO rest controller.
 */
@RestController
public class FuelDtoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FuelDtoController.class);
    private final FuelDtoService fuelDtoService;

    public FuelDtoController(FuelDtoService fuelDtoService) {
        this.fuelDtoService = fuelDtoService;
    }

    /**
     * Get all fuels Dtos.
     *
     * @return Fuel Dtos collection.
     */
        @GetMapping(value = "/fuel_dtos")
    public Collection<FuelDto> fuelDtos() {
        LOGGER.debug("fuelDtos()");
        return fuelDtoService.findAllWithFuelSum();
    }
}

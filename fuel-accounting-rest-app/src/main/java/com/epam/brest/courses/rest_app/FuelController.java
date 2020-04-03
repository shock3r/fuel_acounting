package com.epam.brest.courses.rest_app;

import com.epam.brest.courses.model.Fuel;
import com.epam.brest.courses.rest_app.exception.FuelNotFoundException;
import com.epam.brest.courses.service.FuelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
/**
 * Fuel rest controller.
 */
@RestController
public class FuelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FuelController.class);
    private final FuelService fuelService;

    public FuelController(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    /**
     * Get all fuels.
     *
     * @return fuels list json.
     */
    @GetMapping(value = "/fuels")
    public Collection<Fuel> fuels() {
        LOGGER.debug("fuels()");
        return fuelService.findAll();
    }

    /**
     * Get fuel by id.
     *
     * @param id fuel id.
     * @return json fuel.
     */
    @GetMapping(value="/fuel/{id}")
    public Fuel findFuelById(@PathVariable Integer id){
        LOGGER.debug("findFuelById({})", id);
        return fuelService.findById(id).orElseThrow(() -> new FuelNotFoundException(id));
    }

    /**
     * Add new Fuel into DB.
     * @param fuelName String fuel name.
     * @return Integer number of items that was inserted.
     */
    @PostMapping(path = "/fuels", consumes = "application/json", produces = "application/json")
    public Integer add(@RequestBody String fuelName){
        LOGGER.debug("add({})", fuelName);
        return fuelService.create(
                new Fuel()
                        .setFuelName(fuelName));
    }

}

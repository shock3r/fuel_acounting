package com.epam.brest.courses.service;

import com.epam.brest.courses.model.Fuel;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.epam.brest.courses.constants.FuelConstants.FUEL_NAME_SIZE;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-service.xml", "classpath*:dao.xml"})
public class FuelServiceImplIT {

    @Autowired
    FuelService fuelService;

    @Test
    public void shouldFindAllFuels(){
        List<Fuel> fuels =fuelService.findAll();
        assertNotNull(fuels);
        assertTrue(fuels.size() > 0);
    }

    @Test
    public void shouldfindFuelById(){
        // given
        Fuel fuel = new Fuel()
                .setFuelName(RandomStringUtils.randomAlphabetic(FUEL_NAME_SIZE));
        Integer id = fuelService.create(fuel);

        // when
        Optional<Fuel> fuelOptional = fuelService.findById(id);

        // then
        assertTrue(fuelOptional.isPresent());
        assertEquals(fuelOptional.get().getFuelId(), id);
        assertEquals(fuelOptional.get().getFuelName(), fuel.getFuelName());
    }

    @Test
    public void shouldCreateFuel(){
        Fuel fuel = new Fuel()
                .setFuelName(RandomStringUtils.randomAlphabetic(FUEL_NAME_SIZE));
        Integer id = fuelService.create(fuel);
        assertNotNull(id);
    }

    @Test
    public void shouldUpdateFuel(){
        // given
        Fuel fuel = new Fuel()
                .setFuelName(RandomStringUtils.randomAlphabetic(FUEL_NAME_SIZE));
        Integer id = fuelService.create(fuel);
        assertNotNull(id);

        Optional<Fuel> fuelOptional = fuelService.findById(id);
        Assertions.assertTrue(fuelOptional.isPresent());

        fuelOptional.get()
                .setFuelName(RandomStringUtils.randomAlphabetic(FUEL_NAME_SIZE));

        // when
        int result = fuelService.update(fuelOptional.get());

        // then
        assertTrue(1 == result);
        Optional<Fuel> updatedFuelOptional = fuelService.findById(id);
        Assertions.assertTrue(updatedFuelOptional.isPresent());
        assertEquals(updatedFuelOptional.get().getFuelId(), id);
        assertEquals(updatedFuelOptional.get().getFuelName(), fuelOptional.get().getFuelName());
    }

    @Test
    public void shouldDeleteFuel(){
        //given
        Fuel fuel = new Fuel()
                .setFuelName(RandomStringUtils.randomAlphabetic(FUEL_NAME_SIZE));
        Integer id = fuelService.create(fuel);
        assertNotNull(id);

        List<Fuel> fuels= fuelService.findAll();
        assertNotNull(fuels);

        // when
        int result = fuelService.delete(id);

        //then
        assertTrue(1 == result);

        List<Fuel> currentFuels = fuelService.findAll();
        assertNotNull(currentFuels);

        assertTrue(fuels.size()-1 == currentFuels.size());
    }

}

package com.epam.brest.courses.service;

import com.epam.brest.courses.model.Fuel;
import org.apache.commons.lang3.RandomStringUtils;
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



}

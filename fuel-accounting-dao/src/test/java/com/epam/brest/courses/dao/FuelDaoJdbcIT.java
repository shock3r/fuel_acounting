package com.epam.brest.courses.dao;

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

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml", "classpath:dao.xml"})
public class FuelDaoJdbcIT {

    private final FuelDao fuelDao;

    @Autowired
    public FuelDaoJdbcIT(FuelDao fuelDao) {
        this.fuelDao = fuelDao;
    }

    @Test
    public void shoulFindAllFuels() {
        List<Fuel> fuels = fuelDao.findAll();
        assertNotNull(fuels);
        assertTrue(fuels.size() > 0);
    }

    @Test
    public void shouldFindFuelById(){
        // given
        Fuel fuel = new Fuel()
                .setFuelName(RandomStringUtils.randomAlphabetic(FUEL_NAME_SIZE));
        Integer id = fuelDao.create(fuel);

        // when
        Optional<Fuel> fuelOptional = fuelDao.findById(id);

        // then
        assertTrue(fuelOptional.isPresent());
        assertEquals(fuelOptional.get().getFuelId(), id);
        assertEquals(fuelOptional.get().getFuelName(), fuel.getFuelName());
    }

    @Test
    public void shouldCreateFuel(){
        Fuel fuel = new Fuel()
                .setFuelName(RandomStringUtils.randomAlphabetic(FUEL_NAME_SIZE));
        Integer id = fuelDao.create(fuel);
        assertNotNull(id);
    }

    @Test
    public void shouldNotCreateFuelWithTheSameName(){
        // given
        String fuelName = RandomStringUtils.randomAlphabetic(FUEL_NAME_SIZE);
        Fuel firstNewFuel = new Fuel()
                .setFuelName(fuelName);
        Integer firstId = fuelDao.create(firstNewFuel);
        assertNotNull(firstId);

        Optional<Fuel> firstFuelOptional = fuelDao.findById(firstId);
        Assertions.assertTrue(firstFuelOptional.isPresent());

        // when
        Fuel secondNewFuel = new Fuel()
                .setFuelName(fuelName);
        Integer secondId;
        try {
            secondId = fuelDao.create(secondNewFuel);
        } catch (IllegalArgumentException ex) {
           secondId = null;
        }
        // then
        assertNull(secondId);

    }

    @Test
    public void shouldUpdateFuel(){
        // given
        Fuel fuel = new Fuel()
                .setFuelName(RandomStringUtils.randomAlphabetic(FUEL_NAME_SIZE));
        Integer id = fuelDao.create(fuel);
        assertNotNull(id);

        Optional<Fuel> fuelOptional = fuelDao.findById(id);
        Assertions.assertTrue(fuelOptional.isPresent());

        fuelOptional.get()
                .setFuelName(RandomStringUtils.randomAlphabetic(FUEL_NAME_SIZE));

        // when
        int result = fuelDao.update(fuelOptional.get());

        // then
        assertTrue(1 == result);
        Optional<Fuel> updatedFuelOptional = fuelDao.findById(id);
        Assertions.assertTrue(updatedFuelOptional.isPresent());
        assertEquals(updatedFuelOptional.get().getFuelId(), id);
        assertEquals(updatedFuelOptional.get().getFuelName(), fuelOptional.get().getFuelName());
    }

    @Test
    public void shouldDeleteFuel(){
        //given
        Fuel fuel = new Fuel()
                .setFuelName(RandomStringUtils.randomAlphabetic(FUEL_NAME_SIZE));
        Integer id = fuelDao.create(fuel);
        assertNotNull(id);

        List<Fuel> fuels= fuelDao.findAll();
        assertNotNull(fuels);

        // when
        int result = fuelDao.delete(id);

        //then
        assertTrue(1 == result);

        List<Fuel> currentFuels = fuelDao.findAll();
        assertNotNull(currentFuels);

        assertTrue(fuels.size()-1 == currentFuels.size());
    }
}

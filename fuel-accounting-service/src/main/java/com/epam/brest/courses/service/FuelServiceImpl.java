package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.FuelDao;
import com.epam.brest.courses.model.Fuel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Fuel Service Implementation.
 */
@Service
@Transactional
public class FuelServiceImpl implements FuelService {
    private static Logger LOGGER = LoggerFactory.getLogger(FuelServiceImpl.class);
    private final FuelDao fuelDao;

    public FuelServiceImpl(FuelDao fuelDao) {
        this.fuelDao = fuelDao;
    }

    /**
     * Get all fuels.
     *
     * @return list of all fuels.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Fuel> findAll() {
        LOGGER.debug("findAll()");
        return fuelDao.findAll();
    }

    /**
     * Get all fuels wirh specified id.
     *
     * @param fuelId fuel Id.
     * @return fuel by id.
     */
    @Override
    public Optional<Fuel> findById(Integer fuelId) {
        LOGGER.debug("findById(id:{})",fuelId);
        return fuelDao.findById(fuelId);
    }

    /**
     * Persist new fuel.
     *
     * @param fuel fuel.
     * @return persisted fuel id.
     */
    @Override
    public Integer create(Fuel fuel) {
        LOGGER.debug("create(fuel:{})",fuel);
        return fuelDao.create(fuel);
    }

    /**
     * update fuel.
     *
     * @param fuel fuel.
     * @return number of updated records in database.
     */
    @Override
    public int update(Fuel fuel) {
        LOGGER.debug("update(fuel:{})",fuel);
        return fuelDao.update(fuel);
    }

    /**
     * delete fuel.
     *
     * @param fuelId fuel Id.
     * @return number of deleted records in database.
     */
    @Override
    public int delete(Integer fuelId) {
        LOGGER.debug("delete(fuelId:{})",fuelId);
        return fuelDao.delete(fuelId);
    }
}

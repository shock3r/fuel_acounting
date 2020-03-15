package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.FuelDtoDao;
import com.epam.brest.courses.model.dto.FuelDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Fuel DTO Service Implementation.
 */
@Service
@Transactional
public class FuelDtoServiceImpl implements FuelDtoService {
    private Logger LOGGER = LoggerFactory.getLogger(FuelDtoServiceImpl.class);
    private FuelDtoDao fuelDtoDao;

    public FuelDtoServiceImpl(FuelDtoDao fuelDtoDao) {
        this.fuelDtoDao = fuelDtoDao;
    }

    /**
     * Find all fuels with sum of transports tank capasity by fuel type.
     *
     * @return Fuels DTO list,
     */
    @Override
    @Transactional(readOnly = true)
    public List<FuelDto> findAllWithFuelSum() {
        LOGGER.debug("findAllWithFuelSum");
        return fuelDtoDao.findAllWithFuelSum();
    }
}

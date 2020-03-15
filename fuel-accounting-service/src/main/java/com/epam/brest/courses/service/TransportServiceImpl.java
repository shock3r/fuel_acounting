package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.TransportDao;
import com.epam.brest.courses.model.Transport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TransportServiceImpl implements TransportService {

    private Logger LOGGER = LoggerFactory.getLogger(TransportServiceImpl.class);
    private TransportDao transportDao;

    public TransportServiceImpl(TransportDao transportDao) {
        this.transportDao = transportDao;
    }

    /**
     * Get all transports.
     *
     * @return list of all transports.
     */
    @Override
    public List<Transport> findAll() {
        LOGGER.debug("findAll()");
        return transportDao.findAll();
    }

    /**
     * Get all transports that has transportDate field value in range from dateFrom to dateTo.
     *
     * @param dateFrom Date range from value.
     * @param dateTo   Date ranhe to value.
     * @return List of all transport in range from to specific dates.
     */
    @Override
    public List<Transport> findAllFromDateToDate(Date dateFrom, Date dateTo) {
        LOGGER.debug("findAllFromDateToDate(dateFrom:{}, dateTo:{})", dateFrom, dateTo);
        return transportDao.findAllFromDateToDate(dateFrom, dateTo);
    }

    /**
     * Get all transports with specified fuel id.
     *
     * @param fuelId fuel id.
     * @return transport by fuel id.
     */
    @Override
    public List<Transport> findByFuelId(Integer fuelId) {
        LOGGER.debug("findByFuelId(fuelId:{})", fuelId);
        return transportDao.findByFuelId(fuelId);
    }

    /**
     * Get all transports with specified id.
     *
     * @param transportId transport id.
     * @return transport by id.
     */
    @Override
    public Optional<Transport> findById(Integer transportId) {
        LOGGER.debug("findById(transportId:{})", transportId);
        return transportDao.findById(transportId);
    }

    /**
     * Persist new transport.
     *
     * @param transport transport.
     * @return persisted transport id.
     */
    @Override
    public Integer create(Transport transport) {
        LOGGER.debug("create(transport:{})", transport);
        return transportDao.create(transport);
    }

    /**
     * update transport.
     *
     * @param transport transport.
     * @return number of updated records in database.
     */
    @Override
    public int update(Transport transport) {
        LOGGER.debug("update(transport:{})", transport);
        return transportDao.update(transport);
    }

    /**
     * Delete transport with specified id.
     *
     * @param transportId transport id.
     * @return number of deleted records in database.
     */
    @Override
    public int delete(Integer transportId) {
        LOGGER.debug("delete(transportId:{})", transportId);
        return transportDao.delete(transportId);
    }
}

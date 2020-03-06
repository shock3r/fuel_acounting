package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Transport;

import java.util.List;
import java.util.Optional;

/**
 * TRANSPORT DAO JDBC interface.
 */
public interface TransportDao {

    /**
     * Get all transports.
     *
     * @return list of all transports.
     */
    List<Transport> findAll();

    /**
     * Get all transports with specified fuel id.
     *
     * @param fuelId fuel id.
     * @return transport by fuel id.
     */
    List<Transport> findByFuelId(Integer fuelId);

    /**
     * Get all transports with specified id.
     *
     * @param transportId transport id.
     * @return transport by id.
     */
    Optional<Transport> findById(Integer transportId);

    /**
     * Persist new transport.
     *
     * @param transport transport.
     * @return persisted transport id.
     */
    Integer create(Transport transport);

    /**
     * update transport.
     *
     * @param transport transport.
     * @return number of updated records in database.
     */
    int update(Transport transport);

    /**
     * Delete transport with specified id.
     *
     * @param transportId transport id.
     * @return number of deleted records in database.
     */
    int delete(Integer transportId);

}

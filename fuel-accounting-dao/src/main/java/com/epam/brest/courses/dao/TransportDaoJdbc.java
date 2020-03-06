package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Transport;

import java.util.List;
import java.util.Optional;

/**
 * TRANSPORT DAO JDBC implementation.
 */
public class TransportDaoJdbc implements TransportDao {
    @Override
    public List<Transport> findAll() {
        return null;
    }

    @Override
    public List<Transport> findByFuelId(Integer fuelId) {
        return null;
    }

    @Override
    public Optional<Transport> findById(Integer transportId) {
        return Optional.empty();
    }

    @Override
    public Integer create(Transport transport) {
        return null;
    }

    @Override
    public int update(Transport transport) {
        return 0;
    }

    @Override
    public int delete(Integer transportId) {
        return 0;
    }
}

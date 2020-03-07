package com.epam.brest.courses.dao;


import com.epam.brest.courses.model.Transport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Optional;

/**
 * TRANSPORT DAO JDBC implementation.
 */
public class TransportDaoJdbc implements TransportDao {

    private final Logger LOGGER = LoggerFactory.getLogger(TransportDaoJdbc.class);
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Value("${transport.findAll}")
    private String findAll;
    @Value("${transport.findAllByFuelId}")
    private String findAllByFuelId;
    @Value("${transport.findById}")
    private String findById;
    @Value("${transport.select}")
    private String selectSql;
    @Value("${transport.create}")
    private String createSql;
    @Value("${transport.update}")
    private String updateSql;
    @Value("${transport.delete}")
    private String deleteSql;

    public TransportDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<Transport> findAll() {
        LOGGER.debug("findAll()");
        List<Transport> transports =
                namedParameterJdbcTemplate.query(findAll, BeanPropertyRowMapper.newInstance(Transport.class));
        return transports;
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

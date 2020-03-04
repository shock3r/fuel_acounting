package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Fuel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static com.epam.brest.courses.constants.FuelConstants.COLUMN_FUEL_ID;
import static com.epam.brest.courses.constants.FuelConstants.COLUMN_FUEL_NAME;

public class FuelDaoJdbc implements FuelDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(FuelDaoJdbc.class);
    @Value("${fuel.select}")
    private String selectSql;
    private String createSql;
    private String updateSql;
    private String deleteSql;
    private String findByIdSql;

    private final FuelRowMapper fuelRowMapper = new FuelRowMapper();
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public FuelDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Fuel> findAll() {
        LOGGER.trace("findAll()");
        return namedParameterJdbcTemplate.query(selectSql,fuelRowMapper);
    }

    @Override
    public Optional<Fuel> findById(Integer fuelId) {
        return Optional.empty();
    }

    @Override
    public Integer create(Fuel fuel) {
        return null;
    }

    @Override
    public int update(Fuel fuel) {
        return 0;
    }

    @Override
    public int delete(Integer fuelId) {
        return 0;
    }

    private class FuelRowMapper implements RowMapper<Fuel> {
        @Override
        public Fuel mapRow(ResultSet resultSet, int i) throws SQLException {
            Fuel fuel = new Fuel();
            fuel.setFuelId(resultSet.getInt(COLUMN_FUEL_ID));
            fuel.setFuelName(resultSet.getString(COLUMN_FUEL_NAME));
            return fuel;
        }
    }
}

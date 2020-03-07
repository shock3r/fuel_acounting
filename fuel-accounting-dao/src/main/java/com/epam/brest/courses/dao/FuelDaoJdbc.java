package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Fuel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import static com.epam.brest.courses.constants.FuelConstants.*;

/**
 * FUEL DAO JDBC implementation.
 */
public class FuelDaoJdbc implements FuelDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(FuelDaoJdbc.class);
    @Value("${fuel.select}")
    private String selectSql;
    @Value("${fuel.create}")
    private String createSql;
    @Value("${fuel.update}")
    private String updateSql;
    @Value("${fuel.delete}")
    private String deleteSql;
    @Value("${fuel.findById}")
    private String findByIdSql;

    private final FuelRowMapper fuelRowMapper = new FuelRowMapper();
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public FuelDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Fuel> findAll() {
        LOGGER.debug("findAll()");
        return namedParameterJdbcTemplate.query(selectSql, fuelRowMapper);
    }

    @Override
    public Optional<Fuel> findById(Integer fuelId) {
        LOGGER.debug("findById(id:{})", fuelId);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(FUEL_ID, fuelId);
        List<Fuel> fuelList = namedParameterJdbcTemplate.query(findByIdSql, sqlParameterSource, fuelRowMapper);
        return Optional.ofNullable(DataAccessUtils.uniqueResult(fuelList));
    }

    @Override
    public Integer create(Fuel fuel) {
        LOGGER.debug("create(fuel:{})", fuel);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(FUEL_ID, fuel.getFuelId());
        mapSqlParameterSource.addValue(FUEL_NAME, fuel.getFuelName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(createSql, mapSqlParameterSource,keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public int update(Fuel fuel) {
        LOGGER.debug("update(fuel:{})",fuel);
        Map<String, Object> params = new HashMap<>();
        params.put(FUEL_ID, fuel.getFuelId());
        params.put(FUEL_NAME, fuel.getFuelName());
        return namedParameterJdbcTemplate.update(updateSql, params);
    }

    @Override
    public int delete(Integer fuelId) {
        LOGGER.debug("delete(id:{})", fuelId);
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(FUEL_ID, fuelId);
        return namedParameterJdbcTemplate.update(deleteSql, params);
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

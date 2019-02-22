package com.epam.lowcost.util;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Plane;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public final class FlightRowMapper implements RowMapper<Flight> {
    private RowMapper<Plane> planeRowMapper;

    private FlightRowMapper(RowMapper<Plane> planeRowMapper) {
        this.planeRowMapper = planeRowMapper;
    }

    @Override
    public final Flight mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Flight.builder()
                .id(rs.getLong("FLIGHTS.id"))
                .initialPrice(rs.getLong("initialPrice"))
                .plane(planeRowMapper.mapRow(rs, rowNum))
                .departureAirport(rs.getString("departureAirport").toUpperCase())
                .arrivalAirport(rs.getString("arrivalAirport").toUpperCase())
                .departureDate(rs.getTimestamp("departureDate").toLocalDateTime())
                .arrivalDate(rs.getTimestamp("arrivalDate").toLocalDateTime())
                .businessPrice(rs.getLong("businessPrice"))
                .luggagePrice(rs.getLong("luggagePrice"))
                .placePriorityPrice(rs.getLong(("placePriorityPrice")))
                .isDeleted(rs.getBoolean("isDeleted"))
                .build();
    }
}


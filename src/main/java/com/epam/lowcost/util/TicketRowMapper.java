package com.epam.lowcost.util;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Ticket;
import com.epam.lowcost.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public final class TicketRowMapper implements RowMapper<Ticket> {
    private RowMapper<Flight> flightRowMapper;
    private RowMapper<User> userRowMapper;

    private TicketRowMapper(RowMapper<Flight> flightRowMapper, RowMapper<User> userRowMapper) {
        this.flightRowMapper = flightRowMapper;
        this.userRowMapper = userRowMapper;
    }

    @Override
    public final Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Ticket.builder()
                .id(rs.getLong("TICKETS.id"))
                .flight(flightRowMapper.mapRow(rs, rowNum))
                .user(userRowMapper.mapRow(rs, rowNum))
                .isBusiness(rs.getBoolean("isBusiness"))
                .hasLuggage(rs.getBoolean("hasLuggage"))
                .placePriority(rs.getBoolean("placePriority"))
                .purchaseDate(rs.getTimestamp("purchaseDate").toLocalDateTime())
                .price(rs.getLong("price"))
                .isDeleted(false).build();
    }

}


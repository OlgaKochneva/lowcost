package com.epam.lowcost.util;

import com.epam.lowcost.model.Plane;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PlaneRowMapper implements RowMapper<Plane> {


    private PlaneRowMapper() {
    }

    @Override
    public Plane mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Plane.builder()
                .id(rs.getLong("PLANES.id"))
                .model(rs.getString("model"))
                .businessPlacesNumber(rs.getInt("businessPlacesNumber"))
                .economPlacesNumber(rs.getInt("economPlacesNumber"))
                .isDeleted(rs.getBoolean("isDeleted"))
                .build();
    }
}

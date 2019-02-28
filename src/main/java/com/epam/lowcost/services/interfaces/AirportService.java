package com.epam.lowcost.services.interfaces;

import com.epam.lowcost.model.Airport;

import java.util.List;
import java.util.Map;

public interface AirportService {
    List<Airport> getAllAirports();

    Airport getAirportByCode(String code);

    List <Airport> findAllByCity(String city);


}

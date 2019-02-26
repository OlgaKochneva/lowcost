package com.epam.lowcost.services.interfaces;

import com.epam.lowcost.model.Airport;

import java.util.List;
import java.util.Map;

public interface AirportService {
    List<Airport> getAllAirports();

    Airport getAirportByCode(String code);

    Airport addNewAirport(Map<String, String> params);

    // Airport deleteAirport(Long id);

    Airport updateAirport(Map<String, String> params);

    Map<String,Object> getAirportsByPage(int pageId, int numberOfAirportsOnPage);

}

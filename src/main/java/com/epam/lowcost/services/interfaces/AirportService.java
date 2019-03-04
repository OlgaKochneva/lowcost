package com.epam.lowcost.services.interfaces;

import com.epam.lowcost.model.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface AirportService {
    Page<Airport> getAllAirports(Pageable pageable);

    List<Airport> getAllAirports();

    Airport getAirportByCode(String code);

    Page<Airport> findAllByCity(String city, Pageable pageable);

}

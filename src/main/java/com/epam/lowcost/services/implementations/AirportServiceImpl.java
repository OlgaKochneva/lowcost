package com.epam.lowcost.services.implementations;

import com.epam.lowcost.model.Airport;
import com.epam.lowcost.repositories.AirportRepository;
import com.epam.lowcost.services.interfaces.AirportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;

    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public Airport getAirportByCode(String code) {
        return airportRepository.findAirportByCode(code);
    }
}

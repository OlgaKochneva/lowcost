package com.epam.lowcost.services.implementations;

import com.epam.lowcost.model.Airport;
import com.epam.lowcost.repositories.AirportRepository;
import com.epam.lowcost.services.interfaces.AirportService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;

    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public Page<Airport> getAllAirports(Pageable pageable) {
        return airportRepository.findAll(pageable);
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public Airport getAirportByCode(String code) {
        return airportRepository.findAirportByCode(code);
    }

    @Override
    public Page<Airport> findAllByCity(String city, Pageable pageable) {
        city = city.toLowerCase();
        city = city.substring(0, 1).toUpperCase().concat(city.substring(1, city.length()));
        return airportRepository.findAllByCityEng(city, pageable);
    }


}

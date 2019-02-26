package com.epam.lowcost.services.implementations;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.repositories.FlightRepository;
import com.epam.lowcost.services.interfaces.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.getAllByIsDeletedFalse();
    }

    @Override
    public Flight getById(Long id) {
        return flightRepository.getById(id);
    }

    @Override
    public Flight addNewFlight(Flight flight) {
        return null;
    }

    @Override
    public Flight updateFlight(Flight flight) {
        return null;
    }

    @Override
    public String deleteFlight(Long id) {
        return null;
    }

    @Override
    public List<Flight> getByFromToDate(String departureAirport, String arrivalAirport, LocalDateTime departureDate, LocalDateTime arrivalDate) {
        return null;
    }
}

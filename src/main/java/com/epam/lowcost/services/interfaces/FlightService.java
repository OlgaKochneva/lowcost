package com.epam.lowcost.services.interfaces;

import com.epam.lowcost.model.Airport;
import com.epam.lowcost.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface FlightService {
    Page<Flight> getAllFlights(Pageable pageable);

    List<Flight> getAllFlights();

    Flight getById(Long id);

    Flight addNewFlight(Flight flight);

    Flight updateFlight(Flight flight);

    Flight deleteFlight(Long id);


    List<Flight> getByFromToDate(Airport departureAirport, Airport arrivalAirport, LocalDateTime departureDate, LocalDateTime arrivalDate);


    List<Flight> getFilteredFlightsWithUpdatedPrice(Airport departureAirport, Airport arrivalAirport, LocalDateTime departureDateFrom, LocalDateTime departureDateTo);

    List<Flight> getAllFlightsWithUpdatedPrice();

    Flight getFlightByIdWithUpdatedPrice(Long id);

}

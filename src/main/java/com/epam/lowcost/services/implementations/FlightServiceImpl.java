package com.epam.lowcost.services.implementations;

import com.epam.lowcost.model.Airport;
import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Plane;
import com.epam.lowcost.model.Ticket;
import com.epam.lowcost.repositories.FlightRepository;
import com.epam.lowcost.services.interfaces.AirportService;
import com.epam.lowcost.services.interfaces.FlightService;
import com.epam.lowcost.services.interfaces.PlaneService;
import com.epam.lowcost.services.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final TicketService ticketService;
    private PlaneService planeService;
    public AirportService airportService;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository, PlaneService planeService, AirportService airportService, TicketService ticketService) {
        this.flightRepository = flightRepository;
        this.planeService = planeService;
        this.airportService = airportService;
        this.ticketService = ticketService;

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
        flight.setDeleted(false);
        return flightRepository.save(flight);
    }

    @Override
    public Flight updateFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Flight deleteFlight(Long id) {
        Flight flight = flightRepository.getById(id);
        flight.setDeleted(true);
        return flightRepository.save(flight);

    }

//    @Override
//    public List<Flight> getByFromToDate(String departureAirport, String arrivalAirport, LocalDateTime
//            departureDateFrom, LocalDateTime departureDateTo) {
//        return flightRepository.getByFromToDate(departureAirport, arrivalAirport, departureDateFrom, departureDateTo);
//    }


    private long calculateInitialFlightPriceByDate(long daysBetween, long minPrice) {
        long daysNumber = 60;//min number of days for price rising
        long price;
        if (daysBetween > daysNumber) {
            price = minPrice;
        } else {
            price = (long) (minPrice + (daysNumber - daysBetween) * (daysNumber - daysBetween) * (minPrice / ((double) daysNumber * daysNumber)));
        }

        return price;
    }

    @Override
    public List <Flight> getAllFlightsWithUpdatedPrice() {

        List<Flight> flights = getAllFlights();
        flights.forEach(this::updateFlightPrice);
        flights.forEach(f -> f.getPlane().setEconomPlacesNumber(getNumberOfFreeEconomyPlaces(f)));
        flights.forEach(f -> f.getPlane().setBusinessPlacesNumber(getNumberOfFreeBusinessPlaces(f)));
        return flights;
    }

    private void updateFlightPrice(Flight flight) {
        LocalDateTime dateAfter = flight.getDepartureDate();
        LocalDateTime dateBefore = LocalDateTime.now();
        long daysBetween = DAYS.between(dateBefore, dateAfter);
        long minPrice = flight.getInitialPrice();
        long decreaseBusinessPlacesIncreasePrice = 0;
        long decreaseEconomyPlacesIncreasePrice = 0;
        long decreaseDaysBetweenIncreasePrice = calculateInitialFlightPriceByDate(daysBetween, minPrice);
        // if number of free business places less than quarter of plane business places capacity
        // price rises on quarter of min price
        double priceChangecoefficientForBusinessPlaces = 4.0;
        double priceChangecoefficientForEconomyPlaces = 10.0;
        if (getNumberOfFreeBusinessPlaces(flight) < flight.getPlane().getBusinessPlacesNumber() / priceChangecoefficientForBusinessPlaces) {
            decreaseBusinessPlacesIncreasePrice = (long) (minPrice / priceChangecoefficientForBusinessPlaces);
        }
        // the same with economy places, but coefficient = 10
        if (getNumberOfFreeEconomyPlaces(flight) < flight.getPlane().getEconomPlacesNumber() / priceChangecoefficientForEconomyPlaces) {
            decreaseEconomyPlacesIncreasePrice = (long) (minPrice / priceChangecoefficientForEconomyPlaces);
        }

        flight.setInitialPrice(decreaseBusinessPlacesIncreasePrice +
                decreaseDaysBetweenIncreasePrice + decreaseEconomyPlacesIncreasePrice);

    }

    private int getNumberOfFreeBusinessPlaces(Flight flight) {
        int totalNumber = flight.getPlane().getBusinessPlacesNumber();
        int holdPlaces = ticketService.numberBoughtPlaces(flight.getId(), true);
        return totalNumber - holdPlaces;
    }

    @Override
    public Flight getFlightByIdWithUpdatedPrice(Long id) {
        Flight flight = getById(id);
        updateFlightPrice(flight);
        flight.getPlane().setEconomPlacesNumber(getNumberOfFreeEconomyPlaces(flight));
        flight.getPlane().setBusinessPlacesNumber(getNumberOfFreeBusinessPlaces(flight));
        return flight;
    }

    private int getNumberOfFreeEconomyPlaces(Flight flight) {
        int totalNumber = flight.getPlane().getEconomPlacesNumber();
        int holdPlaces = ticketService.numberBoughtPlaces(flight.getId(), false);
        return totalNumber - holdPlaces;
    }


}

package com.epam.lowcost.services.implementations;

import com.epam.lowcost.model.Airport;
import com.epam.lowcost.model.Flight;
import com.epam.lowcost.repositories.FlightRepository;
import com.epam.lowcost.services.interfaces.TicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FlightServiceImplTest {
    @Mock
    FlightRepository flightRepository;

    @InjectMocks
    FlightServiceImpl flightService;

    @Test
    public void getAllFlights() {
        flightService.getAllFlights();
        verify(flightRepository).getAllByIsDeletedFalse();
    }

    @Test
    public void getById() {
        flightService.getById((long) 1);
        verify(flightRepository).getById((long) 1);
    }

    @Test
    public void addNewFlight() {
        Flight exp_flight = new Flight() {{
            setDeleted(false);
        }};
        Flight act_flight = new Flight();
        when(flightRepository.save(act_flight)).thenReturn(act_flight);
        flightService.addNewFlight(act_flight);
        assertEquals(act_flight, exp_flight);
    }

    @Test
    public void updateFlight() {
        Flight flight = new Flight();
        flightService.updateFlight(flight);
        verify(flightRepository).save(flight);
    }



    @Test
    public void getByFromToDate() {
        Airport departureAirport = new Airport();
        Airport arrivalAirport = new Airport();
        LocalDateTime departureDate = LocalDateTime.now();
        LocalDateTime arrivalDate = LocalDateTime.now();
        flightService.getByFromToDate(departureAirport, arrivalAirport, departureDate, arrivalDate);
        verify(flightRepository).getAllByDepartureAirportAndArrivalAirportAndDepartureDateBetween(departureAirport, arrivalAirport, departureDate, arrivalDate);
    }

}


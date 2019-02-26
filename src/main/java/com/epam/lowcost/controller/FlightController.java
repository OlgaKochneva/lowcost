package com.epam.lowcost.controller;

import com.epam.lowcost.services.interfaces.AirportService;
import com.epam.lowcost.services.interfaces.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightController {

    private FlightService flightService;
    private AirportService airportService;

    @Autowired
    public FlightController(FlightService flightService, AirportService airportService) {

        this.flightService = flightService;
        this.airportService = airportService;
    }
}

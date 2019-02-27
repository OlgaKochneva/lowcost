package com.epam.lowcost.controller;

import com.epam.lowcost.services.interfaces.AirportService;
import com.epam.lowcost.services.interfaces.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import static com.epam.lowcost.util.Endpoints.*;

@Controller
@RequestMapping(value = FLIGHTS)
public class FlightController {

    private FlightService flightService;
    private AirportService airportService;

    @Autowired
    public FlightController(FlightService flightService, AirportService airportService) {
        this.flightService = flightService;
        this.airportService = airportService;
    }
    @RequestMapping(value = ALL, method = RequestMethod.GET)
    public String getAllFlights( ModelMap model) {
        model.addAttribute("flights", flightService.getAllFlights());
        return FLIGHTSPAGE;

    }

    @RequestMapping (method = RequestMethod.GET)
    public String findFlightById(@RequestParam Long id, Model model) {
        model.addAttribute("flight", flightService.getById(id));
        model.addAttribute("airports", airportService.getAllAirports());
        return FLIGHTSETTINGS;
    }
}

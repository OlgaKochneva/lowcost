package com.epam.lowcost.controller;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.services.interfaces.AirportService;
import com.epam.lowcost.services.interfaces.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.epam.lowcost.util.Endpoints.*;

@Controller
public class LoginController {
    private FlightService flightService;
    private AirportService airportService;

    public LoginController(FlightService flightService, AirportService airportService) {
        this.flightService = flightService;
        this.airportService = airportService;
    }

    @RequestMapping(value = LOGIN, method = RequestMethod.GET)
    public String showRegistrationPage() {
        return "loginPage";
    }

    @RequestMapping(value = LOGIN, method = RequestMethod.POST)
    public String enter() {

        return "redirect:/";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        model.addAttribute("flights", flightService.getAllFlights());
        model.addAttribute("airports", airportService.getAllAirports());
        return SEARCHPAGE;
    }
}

package com.epam.lowcost.controller;


import com.epam.lowcost.model.User;
import com.epam.lowcost.services.interfaces.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.epam.lowcost.util.Endpoints.*;

@Controller
@RequestMapping(value = AIRPORT)
@SessionAttributes({"sessionUser", "number"})
public class AirportController {
    AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping(value = ALL)
    public String getAllAirports( ModelMap model) {
        model.addAttribute("airports", airportService.getAllAirports());
        return AIRPORTSPAGE;
    }

    }

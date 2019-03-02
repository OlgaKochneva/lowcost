package com.epam.lowcost.controller;


import com.epam.lowcost.services.interfaces.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = ALL, method = RequestMethod.GET)
    public String getAllAirports(ModelMap model) {
        model.addAttribute("airports", airportService.getAllAirports());
        return AIRPORTSPAGE;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAirportsByCity(@RequestParam String city, ModelMap modelMap) {
        modelMap.addAttribute("airports", airportService.findAllByCity(city));
        return AIRPORTSPAGE;
    }

}

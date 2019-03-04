package com.epam.lowcost.controller;


import com.epam.lowcost.services.interfaces.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public String getAllAirports(ModelMap model, Pageable pageable) {
        model.addAttribute("airports", airportService.getAllAirports(pageable));
        return AIRPORTSPAGE;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAirportsByCity(@RequestParam String city, ModelMap modelMap, Pageable pageable) {
        modelMap.addAttribute("airports", airportService.findAllByCity(city, pageable));
        return AIRPORTSPAGE;
    }

}

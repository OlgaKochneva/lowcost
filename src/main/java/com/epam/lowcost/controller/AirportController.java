package com.epam.lowcost.controller;


import com.epam.lowcost.services.interfaces.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import static com.epam.lowcost.util.Endpoints.*;

@Controller
@RequestMapping(value = AIRPORT)
@SessionAttributes({"sessionUser", "number"})
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AirportController {
    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAirportsByCity(@RequestParam String city, ModelMap modelMap, Pageable pageable) {
        if (city != null && !city.isEmpty()) {
            modelMap.addAttribute("airports", airportService.findAllByCity(city, pageable));
        } else {
            modelMap.addAttribute("airports", airportService.getAllAirports(pageable));
        }
        return AIRPORTSPAGE;
    }
}

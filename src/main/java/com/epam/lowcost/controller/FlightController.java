package com.epam.lowcost.controller;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.services.interfaces.AirportService;
import com.epam.lowcost.services.interfaces.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String getAllFlights(ModelMap model) {
        model.addAttribute("flights", flightService.getAllFlights());
        model.addAttribute("airports", airportService.getAllAirports());

        return FLIGHTSPAGE;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findFlightById(@PathVariable Long id, Model model) {
        model.addAttribute("flight", flightService.getById(id));
        model.addAttribute("airports", airportService.getAllAirports());
        return FLIGHTSETTINGS;
    }


    @PostMapping(value = UPDATE)
    public String updateFlight(@ModelAttribute("flight") Flight flight) {
        flightService.updateFlight(flight);
        return "redirect:" + FLIGHTS + ALL;
    }
    @PostMapping(value = DELETE)
    public String deleteFlight(@RequestParam Long id, Model model) {
        model.addAttribute("flight", flightService.deleteFlight(id));
        return "redirect:" + FLIGHTS + ALL;
    }


//    @GetMapping(value = NEW_TICKET)
//    public String findFlightSetPriceByDate(@RequestParam Long id, Model model) {
//        model.addAttribute("flight", flightService.getFlightByIdWithUpdatedPrice(id));
//        return BUY;
//    }

    @GetMapping(value = RETURN)
    public String goToSearchPage() {
        return "redirect:" + FLIGHTS + FLIGHT;
    }

    @RequestMapping(value = ADD, method = RequestMethod.GET)
    public String addNewFlight(Model model) {
        model.addAttribute("flight", new Flight());
        model.addAttribute("airports", airportService.getAllAirports());
        return ADDFLIGHT;
    }

    @RequestMapping(value = ADD, method = RequestMethod.POST)
    public String addNewFlight(@ModelAttribute("flight") Flight flight,BindingResult bindingResult) {
        flightService.addNewFlight(flight);
        return "redirect:" + FLIGHTS + ALL;
    }


    @RequestMapping(value = FLIGHT, method = RequestMethod.GET)
    public String searchForFlight(ModelMap model) {
        model.addAttribute("flights", flightService.getAllFlights());
        model.addAttribute("airports", airportService.getAllAirports());
        return SEARCHPAGE;
    }


//    @GetMapping(value = SEARCH)
//    public String findFlightByFromToDate(@RequestParam Map<String, String> params, Model model) {
//        if (params.get("departureDateTo").equals(""))
//            params.put(("departureDateTo"), params.get("departureDateFrom"));
//        model.addAttribute("flights", flightService.getFilteredFlightsWithUpdatedPrice
//                (params.get("departureAirport"), params.get("arrivalAirport"),
//                        LocalDate.parse(params.get("departureDateFrom")).atStartOfDay(),
//                        LocalDate.parse(params.get("departureDateTo")).atStartOfDay()));
//        model.addAttribute("airports", airportService.getAllAirports());
//        if (params.get("adminPage").equals("true")) {
//            return FLIGHTSPAGE;
//        }
//        return SEARCHPAGE;
//
//    }





}

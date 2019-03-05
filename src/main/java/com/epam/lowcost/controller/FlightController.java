package com.epam.lowcost.controller;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Ticket;
import com.epam.lowcost.services.interfaces.*;
import com.epam.lowcost.util.FlightValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.epam.lowcost.util.Endpoints.*;

@Controller
@SessionAttributes({"sessionUser"})
@RequestMapping(value = FLIGHTS)
public class FlightController {

    private final FlightService flightService;
    private final AirportService airportService;
    private final TicketService ticketService;
    private final FlightValidator flightValidator;
    private final PlaneService planeService;
    private final UserService userService;

    @Autowired
    public FlightController(FlightService flightService, AirportService airportService,
                            FlightValidator flightValidator, TicketService ticketService,
                            PlaneService planeService, UserService userService) {
        this.flightService = flightService;
        this.airportService = airportService;
        this.flightValidator = flightValidator;
        this.ticketService = ticketService;
        this.planeService = planeService;
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = ALL, method = RequestMethod.GET)
    public String getAllFlights(ModelMap model, Pageable pageable) {
        model.addAttribute("flights", flightService.getAllFlights(pageable));
        model.addAttribute("currentTime", LocalDateTime.now());
        model.addAttribute("airports", airportService.getAllAirports());
        model.addAttribute("flight", new Flight());
        return FLIGHTSPAGE;

    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findFlightById(@PathVariable Long id, Model model) {
        model.addAttribute("flight", flightService.getById(id));
        model.addAttribute("airports", airportService.getAllAirports());
        return FLIGHTSETTINGS;
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = UPDATE)
    public String updateFlight(@ModelAttribute("flight") Flight flight, BindingResult bindingResult, Model model) {
        flightValidator.validate(flight, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("airports", airportService.getAllAirports());
            return FLIGHTSETTINGS;
        }
        flightService.updateFlight(flight);
        return "redirect:" + FLIGHTS + ALL;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = DELETE)
    public String deleteFlight(@RequestParam Long id) {
        flightService.deleteFlight(id);
        return "redirect:" + FLIGHTS + ALL;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = TICKETS + "/{id}", method = RequestMethod.GET)
    public String getAllTicketsForFlight(@PathVariable Long id, ModelMap modelMap) {
        modelMap.addAttribute("tickets", ticketService.getAllTicketsForCurrentFlight(id));
        modelMap.addAttribute("flight", flightService.getById(id));
        return TICKETS_PAGE;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = NEW_TICKET + "/{id}", method = RequestMethod.GET)
    public String findFlightSetPriceByDate(@PathVariable Long id, Model model) {
        model.addAttribute("sessionUser",userService.getSessionUser());
        model.addAttribute("flight", flightService.getFlightByIdWithUpdatedPrice(id));
        model.addAttribute("ticket", new Ticket());
        return BUY;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = ADD, method = RequestMethod.GET)
    public String addNewFlight(Model model) {
        model.addAttribute("flight", new Flight());
        model.addAttribute("airports", airportService.getAllAirports());
        model.addAttribute("planes", planeService.getAllPlanes());
        return ADDFLIGHT;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = ADD, method = RequestMethod.POST)
    public String addNewFlight(@ModelAttribute("flight") Flight flight, BindingResult bindingResult, Model model) {
        flightValidator.validate(flight, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("airports", airportService.getAllAirports());
            model.addAttribute("planes", planeService.getAllPlanes());

            return ADDFLIGHT;
        }
        flightService.addNewFlight(flight);
        return "redirect:" + FLIGHTS + ALL;
    }

    @RequestMapping(value = FLIGHT, method = RequestMethod.GET)
    public String searchForFlight(ModelMap model, Pageable pageable) {
        model.addAttribute("flights", flightService.getAllFlightsWithUpdatedPrice(pageable));
        model.addAttribute("airports", airportService.getAllAirports());
        model.addAttribute("currentTime", LocalDateTime.now());
        model.addAttribute("flight", new Flight());
        return SEARCHPAGE;
    }


    private BindingResult findFlightByFromToDate(Flight flight, Model model, boolean isAdmin,
                                                 BindingResult bindingResult, Pageable pageable) {
        if (flight.getArrivalDate() == null) {
            flight.setArrivalDate(flight.getDepartureDate().plusYears(1).plusDays(1));
        }
        flightValidator.validate(flight, bindingResult);
        if (bindingResult.hasErrors())
            return bindingResult;
        if (isAdmin) {
            model.addAttribute("flights", flightService.getByFromToDate
                    (flight.getDepartureAirport(), flight.getArrivalAirport(),
                            flight.getDepartureDate(), flight.getArrivalDate(), pageable));

        } else {
            model.addAttribute("flights", flightService.getFilteredFlightsWithUpdatedPrice
                    (flight.getDepartureAirport(), flight.getArrivalAirport(),
                            flight.getDepartureDate(), flight.getArrivalDate(),pageable));
        }
        return bindingResult;
    }

    @RequestMapping(value = SEARCH, method = RequestMethod.GET )
    public String findFlightByFromToDateUser(@ModelAttribute("flight") Flight flight, Model model,
                                             BindingResult bindingResult, Pageable pageable) {
        bindingResult = findFlightByFromToDate(flight, model, false, bindingResult, pageable);
        if (bindingResult.hasErrors()) {
            model.addAttribute("flights", flightService.getAllFlightsWithUpdatedPrice(pageable));
            model.addAttribute("airports", airportService.getAllAirports());
            model.addAttribute("currentTime", LocalDateTime.now());

        }
        model.addAttribute("airports", airportService.getAllAirports());
        model.addAttribute("currentTime", LocalDateTime.now());
        return SEARCHPAGE;
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = SEARCH + ADMIN, method = RequestMethod.GET)
    public String findFlightByFromToDateAdmin(@ModelAttribute("flight") Flight flight, Model model,
                                              BindingResult bindingResult, Pageable pageable) {
        bindingResult = findFlightByFromToDate(flight, model, true, bindingResult, pageable);
        if (bindingResult.hasErrors()) {
            model.addAttribute("flights", flightService.getAllFlights(pageable));
            model.addAttribute("airports", airportService.getAllAirports());
            model.addAttribute("currentTime", LocalDateTime.now());
        }
        model.addAttribute("airports", airportService.getAllAirports());
        model.addAttribute("currentTime", LocalDateTime.now());
        return FLIGHTSPAGE;
    }

}

package com.epam.lowcost.controller;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Ticket;
import com.epam.lowcost.services.interfaces.AirportService;
import com.epam.lowcost.services.interfaces.FlightService;
import com.epam.lowcost.services.interfaces.TicketService;
import com.epam.lowcost.util.FlightValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

import static com.epam.lowcost.util.Endpoints.*;

@Controller
@SessionAttributes({"sessionUser"})
@RequestMapping(value = FLIGHTS)
public class FlightController {

    private final FlightService flightService;
    private final AirportService airportService;
    private final TicketService ticketService;
    private final FlightValidator flightValidator;

    @Autowired
    public FlightController(FlightService flightService, AirportService airportService,
                            FlightValidator flightValidator, TicketService ticketService) {
        this.flightService = flightService;
        this.airportService = airportService;
        this.flightValidator = flightValidator;
        this.ticketService = ticketService;
    }

    @RequestMapping(value = ALL, method = RequestMethod.GET)
    public String getAllFlights(ModelMap model) {
        model.addAttribute("flights", flightService.getAllFlights());
        model.addAttribute("currentTime", LocalDateTime.now());
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
    public String updateFlight(@ModelAttribute("flight") Flight flight, BindingResult bindingResult, Model model) {
        flightValidator.validate(flight, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("airports", airportService.getAllAirports());
            return FLIGHTSETTINGS;
        }
        flightService.updateFlight(flight);
        return "redirect:" + FLIGHTS + ALL;
    }

    @PostMapping(value = DELETE)
    public String deleteFlight(@RequestParam Long id) {
        flightService.deleteFlight(id);
        return "redirect:" + FLIGHTS + ALL;
    }

    @RequestMapping(value = TICKETS + "/{id}", method = RequestMethod.GET)
    public String getAllTicketsForFlight(@PathVariable Long id, ModelMap modelMap) {
        modelMap.addAttribute("tickets", ticketService.getAllTicketsForCurrentFlight(id));
        return TICKETS_PAGE;
    }

    @RequestMapping(value = NEW_TICKET + "/{id}", method = RequestMethod.GET)
    public String findFlightSetPriceByDate(@PathVariable Long id, Model model) {
        model.addAttribute("flight", flightService.getFlightByIdWithUpdatedPrice(id));
        model.addAttribute("ticket", new Ticket());
        return BUY;
    }


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
    public String addNewFlight(@ModelAttribute("flight") Flight flight, BindingResult bindingResult, Model model) {
        flightValidator.validate(flight, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("airports", airportService.getAllAirports());
            return ADDFLIGHT;
        }
        flightService.addNewFlight(flight);
        return "redirect:" + FLIGHTS + ALL;
    }


    @RequestMapping(value = FLIGHT, method = RequestMethod.GET)
    public String searchForFlight(ModelMap model) {
        model.addAttribute("flights", flightService.getAllFlightsWithUpdatedPrice());
        model.addAttribute("airports", airportService.getAllAirports());
        model.addAttribute("currentTime", LocalDateTime.now());
        return SEARCHPAGE;
    }


    private BindingResult findFlightByFromToDate(@RequestParam Map<String, String> params, Model model, boolean isAdmin, BindingResult bindingResult) {
        Flight flight = new Flight();
        if (params.get("arrivalDateTo").equals("")) {
            flight.setArrivalDate(LocalDate.parse(params.get("arrivalDateTo")).atStartOfDay().plusYears(1).plusDays(1));
        } else {
            flight.setArrivalDate(LocalDate.parse(params.get("arrivalDateTo")).atStartOfDay().plusDays(1));
        }
        flight.setDepartureDate(LocalDate.parse(params.get("departureDateTo")).atTime(LocalTime.now()));
        flight.setArrivalAirport(airportService.getAirportByCode(params.get("arrivalAirport")));
        flight.setDepartureAirport(airportService.getAirportByCode(params.get("departureAirport")));
        flightValidator.validate(flight,bindingResult);
        if (bindingResult.hasErrors())
            return bindingResult;
        if (isAdmin) {
            model.addAttribute("flights", flightService.getByFromToDate
                    (flight.getDepartureAirport(), flight.getArrivalAirport(),
                            flight.getDepartureDate(), flight.getArrivalDate()));

        } else {
            model.addAttribute("flights", flightService.getFilteredFlightsWithUpdatedPrice
                    (flight.getDepartureAirport(), flight.getArrivalAirport(),
                            flight.getDepartureDate(), flight.getArrivalDate()));
        }
        return bindingResult;
    }

    @RequestMapping(value = SEARCH, method = RequestMethod.GET)
    public String findFlightByFromToDateUser(@RequestParam Map<String, String> params,
            Model model, BindingResult bindingResult) {
        bindingResult = findFlightByFromToDate(params, model, false, bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("flights", flightService.getAllFlightsWithUpdatedPrice());
            model.addAttribute("airports", airportService.getAllAirports());
            model.addAttribute("currentTime", LocalDateTime.now());
        }
        return SEARCHPAGE;
    }


    @RequestMapping(value = SEARCH + ADMIN, method = RequestMethod.GET)
    public String findFlightByFromToDateAdmin(@RequestParam Map<String, String> params, Model model, BindingResult bindingResult) {
        bindingResult = findFlightByFromToDate(params, model, true, bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("flights", flightService.getAllFlights());
            model.addAttribute("airports", airportService.getAllAirports());
            model.addAttribute("currentTime", LocalDateTime.now());
        }
        return FLIGHTSPAGE;
    }

}

package com.epam.lowcost.controller;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Ticket;
import com.epam.lowcost.model.User;
import com.epam.lowcost.services.implementations.EmailServiceImpl;
import com.epam.lowcost.services.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.epam.lowcost.util.Endpoints.*;

@Controller
@RequestMapping(value = TICKETS)
@SessionAttributes(value = "sessionUser")
public class TicketController {
    private final TicketService ticketService;
    private final EmailServiceImpl emailService;

    @Autowired
    public TicketController(TicketService ticketService, EmailServiceImpl emailService) {
        this.ticketService = ticketService;

        this.emailService = emailService;
    }


    @GetMapping(value = FLIGHT)
    public String getAllTickets(@RequestParam long id, ModelMap model) {
        model.addAttribute("tickets", ticketService.getAllTicketsForCurrentFlight(id));
        return TICKETS_PAGE;
    }


    @PostMapping(value = ADD)
    public String addTicket(@RequestParam Map<String, String> params, ModelMap model) {
        Flight flight = Flight.builder()
                .id(Long.parseLong(params.get("flightId")))
                .build();

        User user = (User) model.get("sessionUser");// id сессионного пользователя
        model.addAttribute("ticket", ticketService.addTicket(
                Ticket.builder()
                        .user(user)
                        .flight(flight)
                        .hasLuggage(Boolean.parseBoolean(params.get("hasLuggage")))
                        .placePriority(Boolean.parseBoolean(params.get("placePriority")))
                        .isBusiness(Boolean.parseBoolean(params.get("isBusiness")))
                        .build()));

        return "redirect:" + TICKETS + SELF;
    }

    @PostMapping(value = DELETE)
    public String deleteTicket(@RequestParam long id, ModelMap model) {
        model.addAttribute("message", ticketService.deleteTicketById(id));
        return "redirect:" + TICKETS + FLIGHT;
    }

    @PostMapping(value = PAY)
    public String payTicket(@RequestParam long id, ModelMap model) {
        model.addAttribute("message", ticketService.payTicketById(id));
        return "redirect:" + TICKETS + SELF;
    }

    @PostMapping(value = CANCEL)
    public String cancelTicket(@RequestParam long id, ModelMap model) {
        model.addAttribute("message", ticketService.deleteTicketById(id));
        return "redirect:" + TICKETS + SELF;
    }

    @GetMapping(value = SELF)
    public String getAllUserTickets(ModelMap model) {
        User user = (User) model.get("sessionUser");
        model.addAttribute("currentUserTickets", ticketService.getAllUserTickets(user.getId()));
        return ACCOUNT;
    }
}

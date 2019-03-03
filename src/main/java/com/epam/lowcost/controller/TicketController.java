package com.epam.lowcost.controller;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Ticket;
import com.epam.lowcost.model.User;
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

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping(value = FLIGHT)
    public String getAllTickets(@RequestParam long id, ModelMap model) {
        model.addAttribute("tickets", ticketService.getAllTicketsForCurrentFlight(id));
        return TICKETS_PAGE;
    }


    @PostMapping(value = ADD)
    public String addTicket(@ModelAttribute ("ticket") Ticket ticket, ModelMap model) {
        User user = (User) model.get("sessionUser");
        System.out.println(user.getFirstName());
        ticket.setUser(user);
        Flight flight = (Flight) model.get("flight");
        ticketService.addTicket(ticket);

        return "redirect:" + TICKETS + SELF;
    }

    @PostMapping(value = DELETE)
    public String deleteTicket(@RequestParam long id, ModelMap model) {
        model.addAttribute("message", ticketService.deleteTicketById(id));
        return "redirect:" + TICKETS + FLIGHT;
    }

    @PostMapping(value = PAY+ "/{id}")
    public String payTicket(@PathVariable long id, ModelMap model) {
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

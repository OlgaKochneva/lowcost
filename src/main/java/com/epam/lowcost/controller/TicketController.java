package com.epam.lowcost.controller;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Ticket;
import com.epam.lowcost.model.User;
import com.epam.lowcost.services.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import static com.epam.lowcost.util.Endpoints.*;

@Controller
@RequestMapping(value = TICKETS)
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping(value = FLIGHT)
    public String getAllTickets(Model model) {
        //получаем id самолета и потом удаляем все билеты на него
        model.addAttribute("tickets", ticketService.getAllTicketsForCurrentFlight(1));
        return TICKETS_PAGE;
    }


    @PostMapping(value = ADD)
    public String addTicket(@RequestParam Map<String, String> params, Model model) {
        Flight flight = Flight.builder()
                .id(Long.parseLong(params.get("flightId")))
                .build();

        User user = User.builder()
                .id((long) 1) // id сессионного пользователя
                .build();

        model.addAttribute("ticket", ticketService.addTicket(
                Ticket.builder()
                        .user(user)
                        .flight(flight)
                        .hasLuggage(Boolean.parseBoolean(params.get("hasLuggage")))
                        .placePriority(Boolean.parseBoolean(params.get("placePriority")))
                        .isBusiness(Boolean.parseBoolean(params.get("isBusiness")))
                        .build()));

        model.addAttribute("message", "Ticket successfully added");
        return "redirect:" + TICKETS + SELF;
    }

    @PostMapping(value = DELETE)
    public String deleteTicket(@RequestParam long id, Model model) {
        model.addAttribute("message", ticketService.deleteTicketById(id));
        return "redirect:" + TICKETS + FLIGHT;
    }

    @PostMapping(value = PAY)
    public String payTicket(@RequestParam long id, Model model) {
        model.addAttribute("message", ticketService.payTicketById(id));
        return "redirect:" + TICKETS + SELF;
    }

    @PostMapping(value = CANCEL)
    public String cancelTicket(@RequestParam long id, Model model) {
        model.addAttribute("message", ticketService.deleteTicketById(id));
        return "redirect:" + TICKETS + SELF;
    }

    @GetMapping(value = SELF)
    public String getAllUserTickets(Model model) {
        model.addAttribute("currentUserTickets", ticketService.getAllUserTickets(1));//сессионный юзер
        return ACCOUNT;
    }
}

package com.epam.lowcost.controller;

import com.epam.lowcost.services.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class TicketController {
    @Autowired
    TicketService ticketService;
}

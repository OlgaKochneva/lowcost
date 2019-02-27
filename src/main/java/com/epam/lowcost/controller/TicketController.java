package com.epam.lowcost.controller;

import com.epam.lowcost.services.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;


@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class TicketController {
    @Autowired
    TicketService ticketService;

}

package com.epam.lowcost.controller;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Ticket;
import com.epam.lowcost.model.User;
import com.epam.lowcost.services.implementations.EmailServiceImpl;
import com.epam.lowcost.services.implementations.PDFService;
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
    private final PDFService pdfService;
    private final EmailServiceImpl emailService;

    @Autowired
    public TicketController(TicketService ticketService, PDFService pdfService, EmailServiceImpl emailService) {
        this.ticketService = ticketService;
        this.pdfService = pdfService;
        this.emailService = emailService;
    }


    @GetMapping(value = PDF)
    public String createPDFTicket(@RequestParam long ticketId, @RequestParam String userEmail){
        try {
            pdfService.createPDF_Ticket(ticketService.getTicketById(ticketId));
            emailService.sendMessageWithAttachment(userEmail,
                    String.format("Ticket for order №%s",ticketId),
                    String.format("Ticket for order №%s in attachments.",ticketId),String.format("src/main/webapp/resources/tickets_pdf/Ticket_№%d.pdf",ticketId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:" + TICKETS + SELF;
    }

    @GetMapping(value = FLIGHT)
    public String getAllTickets(@RequestParam long id, ModelMap model) {
        model.addAttribute("tickets", ticketService.getAllTicketsForCurrentFlight(id));
        return TICKETS_PAGE;
    }


    @PostMapping(value = ADD)
    public String addTicket(@ModelAttribute ("ticket") Ticket ticket, ModelMap model) {
        User user = (User) model.get("sessionUser");
        ticket.setUser(user);
        ticketService.addTicket(ticket);
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

package com.epam.lowcost.controller;

import com.epam.lowcost.model.Ticket;
import com.epam.lowcost.model.User;
import com.epam.lowcost.services.implementations.EmailServiceImpl;
import com.epam.lowcost.services.implementations.PDFService;
import com.epam.lowcost.services.interfaces.TicketService;
import com.epam.lowcost.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static com.epam.lowcost.util.Endpoints.*;

@Controller
@RequestMapping(value = TICKETS)
@SessionAttributes(value = "sessionUser")
public class TicketController {
    private final TicketService ticketService;
    private final PDFService pdfService;
    private final EmailServiceImpl emailService;
    private final UserService userService;


    @Autowired
    public TicketController(TicketService ticketService, PDFService pdfService, EmailServiceImpl emailService,UserService userService) {
        this.ticketService = ticketService;
        this.pdfService = pdfService;
        this.emailService = emailService;
        this.userService = userService;
    }


    @GetMapping(value = PDF)
    public String createPDFTicket(@RequestParam long ticketId, @RequestParam String userEmail, Model model){
        try {
            pdfService.createPDF_Ticket(ticketService.getTicketById(ticketId));
            emailService.sendMessageWithAttachment(userEmail,
                    String.format("Ticket for order №%s",ticketId),
                    String.format("Ticket for order №%s in attachments.",ticketId),String.format("src/main/webapp/resources/tickets_pdf/Ticket_№%d.pdf",ticketId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("message","sent");
        User user = userService.getSessionUser();
        model.addAttribute("currentUserTickets", ticketService.getAllUserTickets(user.getId()));
        model.addAttribute("sessionUser",user);

        return ACCOUNT;
    }

    @RequestMapping(value = DOWNLOAD, method = RequestMethod.GET)
    public ResponseEntity<Object> downloadFile(@RequestParam long ticketId) throws IOException {
        try {
            pdfService.createPDF_Ticket(ticketService.getTicketById(ticketId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String filename = String.format("src/main/webapp/resources/tickets_pdf/Ticket_№%d.pdf", ticketId);
        File file = new File(filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(
                MediaType.parseMediaType("application/txt")).body(resource);
    }

    @GetMapping(value = FLIGHT)
    public String getAllTickets(@RequestParam long id, ModelMap model) {
        model.addAttribute("tickets", ticketService.getAllTicketsForCurrentFlight(id));
        return TICKETS_PAGE;
    }


    @PostMapping(value = ADD)
    public String addTicket(@ModelAttribute ("ticket") Ticket ticket, ModelMap model) {
        User user = userService.getSessionUser();
        model.addAttribute("sessionUser",user);
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
    public String cancelTicket(@RequestParam(required = false) long id, ModelMap model) {
        model.addAttribute("message", ticketService.deleteTicketById(id));
        return "redirect:" + TICKETS + SELF;
    }

    @GetMapping(value = SELF)
    public String getAllUserTickets(ModelMap model) {
        User user = userService.getSessionUser();
        /*Implement paging here*/
        model.addAttribute("currentUserTickets", ticketService.getAllUserTickets(user.getId()));
        model.addAttribute("sessionUser",user);
        return ACCOUNT;
    }
}

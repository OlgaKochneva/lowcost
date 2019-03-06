package com.epam.lowcost.services.implementations;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Ticket;
import com.epam.lowcost.model.User;
import com.epam.lowcost.repositories.TicketRepository;
import com.epam.lowcost.services.interfaces.FlightService;
import com.epam.lowcost.services.interfaces.TicketService;
import com.epam.lowcost.services.interfaces.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final UserService userService;
    @Autowired
    @Getter
    @Setter
    private FlightService flightService;
    private ResourceBundle bundle = ResourceBundle.getBundle("messages");

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Ticket getTicketById(long id) {
        return ticketRepository.findById(id);
    }

    @Override
    @Transactional
    public String payTicketById(long id) {
        Ticket ticketForPayment = ticketRepository.findById(id);
        ticketForPayment.setPaid(true);
        ticketForPayment.setPurchaseDate(LocalDateTime.now());
        return bundle.getString("lang.ticketSuccessfullyPaid");
    }

    @Override
    public Page<Ticket> getAllUserTickets(long userId, Pageable pageable) {
        return ticketRepository.findByUser_IdAndIsDeleted(userId, false, pageable);
    }


    @Override
    @Transactional
    public Ticket addTicket(Ticket ticket) {
        Flight flight = flightService.getById(ticket.getFlight().getId());
        User user = userService.getById(ticket.getUser().getId());
        ticket.setFlight(flight);
        ticket.setUser(user);
        ticket.setPrice(flightService.getUpdatedFlightPrice(flight));
        ticket.setPrice(countPrice(ticket));
        ticket.setDeleted(false);
        ticket.setPaid(false);
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    public List<Ticket> getAllTicketsForCurrentFlight(long flightId) {
        return ticketRepository.findByFlight_IdAndIsDeleted(flightId, false);
    }

    @Override
    @Transactional
    public String deleteTicketById(long id) {
        Ticket ticketToDelete = ticketRepository.findById(id);
        ticketToDelete.setDeleted(true);
        return bundle.getString("lang.ticketSuccessfullyReturned");
    }

    @Override
    public int getNumberBoughtPlacesForEachClass(long flightId, boolean isBusiness) {
        return ticketRepository.countAllByFlight_IdAndBusinessAndIsDeleted(flightId, isBusiness, false);
    }

    @Override
    @Transactional
    public String deleteTicketsByFlightId(long flightId) {
        getAllTicketsForCurrentFlight(flightId).forEach(t -> t.setDeleted(true));
        return bundle.getString("lang.allTicketsDelete") + flightId;
    }

    private long countPrice(Ticket ticket) {
        long price = ticket.getPrice();
        Flight flight = ticket.getFlight();
        if (ticket.isHasLuggage()) {
            price += flight.getLuggagePrice();
        }
        if (ticket.isPlacePriority()) {
            price += flight.getPlacePriorityPrice();
        }
        if (ticket.isBusiness()) {
            price += flight.getBusinessPrice();
        }
        return price;
    }

}

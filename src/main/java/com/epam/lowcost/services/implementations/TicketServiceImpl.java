package com.epam.lowcost.services.implementations;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Ticket;
import com.epam.lowcost.repositories.TicketRepository;
import com.epam.lowcost.services.interfaces.FlightService;
import com.epam.lowcost.services.interfaces.TicketService;
import com.epam.lowcost.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private FlightService flightService;
    private UserService userService;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    @Override
    public List<Ticket> getAllUserTickets(long userId) {
        return ticketRepository.findByUser_Id(userId);
    }


    @Override
    public Ticket addTicket(Ticket ticket) {
        return null;
    }

    @Override
    public List<Ticket> getAllTicketsForCurrentFlight(long flightId) {
        return ticketRepository.findByFlight_Id(flightId);
    }

    @Override
    public String deleteTicketById(long id) {
        Ticket ticketToDelete = ticketRepository.findById(id);
        ticketToDelete.setDeleted(true);
        ticketRepository.save(ticketToDelete);
        return "Ticket successfully returned";
    }

    @Override
    public long numberBoughtPlacesForEachClass(long flightId) {
        return 0;
    }

    @Override
    public String deleteTicketsByFlightId(long flightId) {
        List<Ticket> ticketsToDelete = getAllTicketsForCurrentFlight(flightId);
        ticketsToDelete.forEach(f -> deleteTicketById(f.getId()));
        return "All tickets for flight " + flightId + "deleted";
    }


    @Override
    public String deleteTicketsByUserId(long userId) {
        List<Ticket> ticketsToDelete = getAllUserTickets(userId);
        ticketsToDelete.forEach(f -> deleteTicketById(f.getId()));
        return "All tickets for flight " + userId + "deleted";
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


    @Override
    public long countAllTickets() {
        return ticketRepository.count();
    }


}

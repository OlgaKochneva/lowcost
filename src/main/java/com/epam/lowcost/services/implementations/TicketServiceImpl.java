package com.epam.lowcost.services.implementations;

import com.epam.lowcost.model.Ticket;
import com.epam.lowcost.repositories.TicketRepository;
import com.epam.lowcost.services.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    @Override
    public int numberBoughtPlaces(long flightId, boolean isBusiness) {
        return 0;
    }

    @Override
    public boolean deleteTicketsByFlightId(long id) {
        return false;
    }

    @Override
    public boolean deleteTicketsByUserId(long id) {
        return false;
    }

    @Override
    public int countTickets() {
        return 0;
    }

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    @Override
    public List<Ticket> getAllUserTickets(long userId) {
        return null;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return null;
    }

    @Override
    public Ticket addTicket(Ticket ticket) {
        return null;
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        return null;
    }

    @Override
    public String deleteTicket(long id) {
        return null;
    }
}

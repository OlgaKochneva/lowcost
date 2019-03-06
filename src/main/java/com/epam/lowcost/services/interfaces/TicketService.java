package com.epam.lowcost.services.interfaces;

import com.epam.lowcost.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TicketService {
    public Ticket getTicketById(long id);

    Page<Ticket> getAllUserTickets(long userId, Pageable pageable);

    List<Ticket> getAllTicketsForCurrentFlight(long flightId);

    Ticket addTicket(Ticket ticket);

    String deleteTicketById(long id);

    int getNumberBoughtPlacesForEachClass(long flightId, boolean isBusiness);

    String deleteTicketsByFlightId(long id);

    String payTicketById(long id);
}

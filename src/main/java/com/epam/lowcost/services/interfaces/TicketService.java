package com.epam.lowcost.services.interfaces;

import com.epam.lowcost.model.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> getAllUserTickets(long userId);

    List<Ticket> getAllTicketsForCurrentFlight(long flightId);

    Ticket addTicket(Ticket ticket);

    String deleteTicketById(long id);

    long numberBoughtPlacesForEachClass(long flightId, boolean isBusiness);

    String deleteTicketsByFlightId(long id);

    String deleteTicketsByUserId(long id);

    long countAllTickets();
}

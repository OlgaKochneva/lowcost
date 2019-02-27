package com.epam.lowcost.services.interfaces;

import com.epam.lowcost.model.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> getAllUserTickets(long userId);

    List<Ticket> getAllTickets();

    Ticket addTicket(Ticket ticket);

    Ticket updateTicket(Ticket ticket);

    String deleteTicket(long id);

    int numberBoughtPlaces(long flightId, boolean isBusiness);

    boolean deleteTicketsByFlightId(long id);

    boolean deleteTicketsByUserId(long id);

    int countTickets();
}

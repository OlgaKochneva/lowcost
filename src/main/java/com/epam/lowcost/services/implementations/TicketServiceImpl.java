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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public boolean deleteTicketsByUserId(long id) {
        return false;
    }

    @Override
    public int countTickets() {
        return 0;
    }

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository,UserService userService) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
    }


    @Override
    public List<Ticket> getAllUserTickets(long userId) {
        return ticketRepository.findByUser_Id(userId);
    }


    @Override
    @Transactional
    public Ticket addTicket(Ticket ticket) {
        Flight flight = flightService.getFlightByIdWithUpdatedPrice(ticket.getFlight().getId());
        User user = userService.getById(ticket.getUser().getId());
        ticket.setFlight(flight);
        ticket.setUser(user);
        ticket.setPrice(flight.getInitialPrice());
        ticket.setPrice(countPrice(ticket));
        ticket.setDeleted(false);
        return ticket;
    }

    @Override
    public List<Ticket> getAllTicketsForCurrentFlight(long flightId) {
        return ticketRepository.findByFlight_IdAndAndIsDeleted(flightId, false);
    }

    @Override
    public String deleteTicketById(long id) {
        Ticket ticketToDelete = ticketRepository.findById(id);
        ticketToDelete.setDeleted(true);
        ticketRepository.save(ticketToDelete);
        return bundle.getString("lang.ticketSuccessfullyReturned");
    }

    @Override
    public int getNumberBoughtPlacesForEachClass(long flightId, boolean isBusiness) {
        return ticketRepository.countAllByFlight_IdAndIsBusinessAndIsDeleted(flightId, isBusiness, false);
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

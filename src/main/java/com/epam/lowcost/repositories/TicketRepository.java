package com.epam.lowcost.repositories;

import com.epam.lowcost.model.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUser_IdAndIsDeleted(long userId, boolean isDeleted);

    List<Ticket> findAll();

    List<Ticket> findByFlight_IdAndAndIsDeleted(long flightId, boolean isDeleted);

    int countAllByFlight_IdAndIsBusinessAndIsDeleted(long flightId, boolean isBusiness, boolean isDeleted);

    Ticket findById(long ticketId);

}

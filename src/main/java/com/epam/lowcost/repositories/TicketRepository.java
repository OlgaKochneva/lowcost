package com.epam.lowcost.repositories;

import com.epam.lowcost.model.Ticket;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Page<Ticket> findByUser_IdAndIsDeleted(long userId, boolean isDeleted, Pageable pageable);

    List<Ticket> findAll();

    List<Ticket> findByFlight_IdAndIsDeleted(long flightId, boolean isDeleted);

    int countAllByFlight_IdAndBusinessAndIsDeleted(long flightId, boolean isBusiness, boolean isDeleted);

    Ticket findById(long ticketId);

}

package com.epam.lowcost.repositories;

import com.epam.lowcost.model.Airport;
import com.epam.lowcost.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    Flight getById(Long id);
    Page<Flight> getAllByIsDeletedFalse(Pageable pageable);
    List<Flight> getAllByIsDeletedFalse();
    List<Flight> getAllByDepartureAirportAndArrivalAirportAndDepartureDateBetween(Airport departureAirport,
                                                                                  Airport arrivalAirport,
                                                                                  LocalDateTime departureDateFrom,
                                                                                  LocalDateTime departureDateTo);
    Page<Flight> getAllByDepartureAirportAndArrivalAirportAndDepartureDateBetween(Airport departureAirport,
                                                                                  Airport arrivalAirport,
                                                                                  LocalDateTime departureDateFrom,
                                                                                  LocalDateTime departureDateTo,
                                                                                  Pageable pageable);


    List<Flight> getAllByPlaneId(long id);






}

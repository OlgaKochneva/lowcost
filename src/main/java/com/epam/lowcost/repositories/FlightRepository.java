package com.epam.lowcost.repositories;

import com.epam.lowcost.model.Airport;
import com.epam.lowcost.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    Flight getById(Long id);
    List<Flight> getAllByIsDeletedFalse();
    List<Flight> getAllByDepartureAirportAndArrivalAirportAndDepartureDateBetween(Airport departureAirport,
                                                                                  Airport arrivalAirport,
                                                                                  LocalDateTime departureDateFrom,
                                                                                  LocalDateTime departureDateTo);





}

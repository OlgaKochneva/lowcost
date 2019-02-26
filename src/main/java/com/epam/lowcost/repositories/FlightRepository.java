package com.epam.lowcost.repositories;

import com.epam.lowcost.model.Airport;
import com.epam.lowcost.model.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {
    Flight getById(Long id);
    List<Flight> getAllByIsDeletedFalse();
    List<Flight> getAllByDepartureAirportAndArrivalAirport(Airport departureAirport, Airport arrivalAirport);

    @Override
    <S extends Flight> S save(S entity);


}

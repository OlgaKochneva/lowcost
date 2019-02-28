package com.epam.lowcost.repositories;

import com.epam.lowcost.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport,String > {
    List <Airport> findAll();
    Airport findAirportByCode(String code);
}

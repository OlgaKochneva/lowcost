package com.epam.lowcost.services.implementations;

import com.epam.lowcost.model.Plane;
import com.epam.lowcost.repositories.PlaneRepository;
import com.epam.lowcost.services.interfaces.FlightService;
import com.epam.lowcost.services.interfaces.PlaneService;
import com.epam.lowcost.services.interfaces.TicketService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaneServiceImpl implements PlaneService {
    private final PlaneRepository planeRepository;
    @Autowired
    @Getter
    @Setter
    private FlightService flightService;

    @Autowired
    public PlaneServiceImpl(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    @Override
    public Page<Plane> getAllPlanes(Pageable pageable) {
        return planeRepository.getAllByIsDeletedFalse(pageable);
    }

    @Override
    public List<Plane> getAllPlanes() {
        return planeRepository.getAllByIsDeletedFalse();
    }

    @Override
    public Plane getById(long planeId) {
        return planeRepository.getById(planeId);
    }

    @Override
    public void addPlane(Plane plane) {
        plane.setDeleted(false);
        planeRepository.save(plane);
    }

    @Override
    public void updatePlane(Plane plane) {
        planeRepository.save(plane);
    }

    @Override
    public void deletePlane(long planeId) {
        Plane plane = planeRepository.getById(planeId);
        plane.setDeleted(true);
        flightService.deleteFlightByPlaneId(planeId);
        planeRepository.save(plane);
    }
}

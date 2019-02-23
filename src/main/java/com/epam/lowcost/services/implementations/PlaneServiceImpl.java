package com.epam.lowcost.services.implementations;

import com.epam.lowcost.model.Plane;
import com.epam.lowcost.repositories.PlaneRepository;
import com.epam.lowcost.services.interfaces.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaneServiceImpl implements PlaneService {
    private final PlaneRepository repository;

    @Autowired
    public PlaneServiceImpl(PlaneRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Plane> getAllPlanes() {
        return (List<Plane>) repository.findAll();
    }

    @Override
    public Plane getById(long planeId) {
        return repository.findById(planeId).orElse(Plane.builder().build());
    }

    @Override
    public Plane addPlane(Plane plane) {
        return null;
    }

    @Override
    public Plane updatePlane(Plane plane) {
        return null;
    }

    @Override
    public void deletePlane(long planeId) {

    }
}

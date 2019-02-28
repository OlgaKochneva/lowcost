package com.epam.lowcost.services.implementations;

import com.epam.lowcost.model.Plane;
import com.epam.lowcost.repositories.PlaneRepository;
import com.epam.lowcost.services.interfaces.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaneServiceImpl implements PlaneService {
    private final PlaneRepository planeRepository;

    @Autowired
    public PlaneServiceImpl(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    @Override
    public List<Plane> getAllPlanes() {
        return planeRepository.findAll();
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

    }
}

package com.epam.lowcost.services.interfaces;

import com.epam.lowcost.model.Plane;

import java.util.List;

public interface PlaneService {
    List<Plane> getAllPlanes();

    Plane getById(long planeId);

    void addPlane(Plane plane);

    void updatePlane(Plane plane);

    void deletePlane(long planeId);
}

package com.epam.lowcost.services.interfaces;

import com.epam.lowcost.model.Plane;

import java.util.List;

public interface PlaneService {
    List<Plane> getAllPlanes();

    Plane getById(long planeId);

    Plane addPlane(Plane plane);

    Plane updatePlane(Plane plane);

    String deletePlane(long planeId);
}

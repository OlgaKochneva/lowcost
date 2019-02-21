package com.epam.lowcost.DAO.interfaces;

import com.epam.lowcost.model.Plane;

import java.util.List;

public interface PlaneDAO {

    List<Plane> getAllPlanes();

    Plane getById(long planeId);

    Plane addPlane(Plane plane);

    Plane updatePlane(Plane plane);

    Plane deletePlane(long planeId);
}

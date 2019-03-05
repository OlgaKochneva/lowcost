package com.epam.lowcost.services.interfaces;

import com.epam.lowcost.model.Plane;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlaneService {
    Page<Plane> getAllPlanes(Pageable pageable);

    List<Plane> getAllPlanes();

    Plane getById(long planeId);

    void addPlane(Plane plane);

    void updatePlane(Plane plane);

    void deletePlane(long planeId);
}

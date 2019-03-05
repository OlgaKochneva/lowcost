package com.epam.lowcost.services.interfaces;

import com.epam.lowcost.model.Plane;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaneService {
    Page<Plane> getAllPlanes(Pageable pageable);

    Plane getById(long planeId);

    void addPlane(Plane plane);

    void updatePlane(Plane plane);

    void deletePlane(long planeId);
}

package com.epam.lowcost.repositories;

import com.epam.lowcost.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, Long> {
    Plane getById(Long planeId);
    List<Plane> getAllByIsDeletedFalse();
}

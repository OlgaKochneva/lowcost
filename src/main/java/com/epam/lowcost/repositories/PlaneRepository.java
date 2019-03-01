package com.epam.lowcost.repositories;

import com.epam.lowcost.model.Plane;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaneRepository extends CrudRepository<Plane, Long> {
    Plane getById(Long id);
    List<Plane> getAllByIsDeletedFalse();


}

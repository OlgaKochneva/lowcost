package com.epam.lowcost.services.implementations;

import com.epam.lowcost.model.Plane;
import com.epam.lowcost.repositories.PlaneRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlaneServiceImplTest {
    @Mock
    private PlaneRepository planeRepository;

    @InjectMocks
    private PlaneServiceImpl planeService;

    @Test
    public void getAllPlanes() {
        planeService.getAllPlanes();

        verify(planeRepository).getAllByIsDeletedFalse();
    }

    @Test
    public void getById() {
    }

    @Test
    public void addPlane() {
        Plane plane = new Plane();
        when(planeRepository.save(plane)).thenReturn(new Plane(){{setDeleted(false);}});

    }

    @Test
    public void updatePlane() {
    }

    @Test
    public void deletePlane() {
    }
}
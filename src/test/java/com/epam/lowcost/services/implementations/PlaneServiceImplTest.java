package com.epam.lowcost.services.implementations;

import com.epam.lowcost.model.Plane;
import com.epam.lowcost.repositories.PlaneRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
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
        planeService.getById(1);
        verify(planeRepository).getById((long)1);
    }

    @Test
    public void addPlane() {
        Plane expectedPlane = new Plane(){{setDeleted(false);}};
        Plane actualPlane = new Plane();
        when(planeRepository.save(actualPlane)).thenReturn(actualPlane);
        planeService.addPlane(actualPlane);
        assertEquals(expectedPlane, actualPlane);
    }

    @Test
    public void updatePlane() {
        Plane plane = new Plane();
        planeService.updatePlane(plane);
        verify(planeRepository).save(plane);
    }

    @Test
    public void deletePlane() {
        Plane actualPlane = new Plane();
        Plane expectedPlane = new Plane(){{setDeleted(true);}};
        when(planeRepository.getById((long)1)).thenReturn(actualPlane);
        when(planeRepository.save(actualPlane)).thenReturn(actualPlane);
        planeService.deletePlane(1);
        assertEquals(expectedPlane, actualPlane);
    }
}
package com.epam.lowcost.services.implementations;

import com.epam.lowcost.repositories.AirportRepository;
import com.epam.lowcost.services.interfaces.AirportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AirportServiceImplTest {
    @Mock
    AirportRepository airportRepository;

    @InjectMocks
    AirportServiceImpl airportService;

    @Test
    public void getAllAirports(){
        airportService.getAllAirports();
        verify(airportRepository).findAll();
    }

    @Test
    public void getAirportByCode(){
        String code = "DME";
        airportService.getAirportByCode(code);
        verify(airportRepository).findAirportByCode(code);
    }

    @Test
    public void getAllByCity(){
        String city="Moscow";
        airportService.findAllByCity(city);
        verify(airportRepository).findAllByCityEng(city);
    }
}

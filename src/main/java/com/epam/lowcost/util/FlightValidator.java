package com.epam.lowcost.util;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.services.interfaces.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Component
public class FlightValidator implements Validator {
    private final FlightService flightService;

    @Autowired
    public FlightValidator (FlightService flightService) {
        this.flightService = flightService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Flight.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Flight flight = (Flight) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "departureAirport", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "arrivalAirport", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "departureDate", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "arrivalDate", "NotEmpty");
       /* ValidationUtils.rejectIfEmptyOrWhitespace(errors, "plane", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "initialPrice", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "placePriorityPrice", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "luggagePrice", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "businessPrice", "NotEmpty");*/
        if (flight.getDepartureDate().isBefore(LocalDateTime.now())){
            errors.rejectValue("departureDate", "Date.incorrect.pastNow");
        }

        if (flight.getDepartureDate().isAfter(flight.getArrivalDate())){
            errors.rejectValue("arrivalDate", "Date.incorrect");
        }
        if (flight.getDepartureAirport()!=null && flight.getArrivalAirport()!=null&& flight.getDepartureAirport().equals(flight.getArrivalAirport())){
            errors.rejectValue("arrivalAirport", "Airport.incorrect.arrivalAirport");
        }




    }
}

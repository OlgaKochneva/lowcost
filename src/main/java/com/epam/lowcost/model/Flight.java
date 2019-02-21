package com.epam.lowcost.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {
    private long id;
    private Plane plane;
    private long initialPrice;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private String departureAirport;
    private String arrivalAirport;
    private boolean isDeleted;
    private long placePriorityPrice;
    private long businessPrice;
    private long luggagePrice;
}

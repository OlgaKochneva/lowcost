package com.epam.lowcost.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "FLIGHTS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "PLANE_ID")
    private Plane plane;

    private long initialPrice;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;

    @ManyToOne
    @JoinColumn(name = "DEPARTURE_AIRPORT")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "ARRIVAL_AIRPORT")
    private Airport arrivalAirport;

    private boolean isDeleted;

    private long placePriorityPrice;

    private long businessPrice;

    private long luggagePrice;
}

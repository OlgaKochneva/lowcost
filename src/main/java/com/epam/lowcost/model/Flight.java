package com.epam.lowcost.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "FLIGHT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "initialPrice", nullable = false)
    private long initialPrice;

    @Column(name = "planeId", nullable = false)
    private Plane plane;

    @Column(name = "departureDate", nullable = false)
    private LocalDateTime departureDate;

    @Column(name = "arrivalDate", nullable = false)
    private LocalDateTime arrivalDate;

    @Column(name = "departureAirport", nullable = false)
    private String departureAirport;

    @Column(name = "arrivalAirport", nullable = false)
    private String arrivalAirport;

    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted;

    @Column(name = "placePriorityPrice", nullable = false)
    private long placePriorityPrice;

    @Column(name = "businessPrice", nullable = false)
    private long businessPrice;

    @Column(name = "luggagePrice", nullable = false)
    private long luggagePrice;
}

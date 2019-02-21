package com.epam.lowcost.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "FLIGHTS")
@Data
public class Flight {
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "planeId")
    private Plane plane;

    @Column(name = "initialPrice")
    private long initialPrice;

    @Column(name = "departureDate")
    private LocalDateTime departureDate;

    @Column(name = "arrivalDate")
    private LocalDateTime arrivalDate;

    @Column(name = "departureAirport")
    private String departureAirport;

    @Column(name = "arrivalAirport")
    private String arrivalAirport;

    @Column(name = "isDeleted")
    private boolean isDeleted;

    @Column(name = "placePriorityPrice")
    private long placePriorityPrice;

    @Column(name = "businessPrice")
    private long businessPrice;

    @Column(name = "luggagePrice")
    private long luggagePrice;
}

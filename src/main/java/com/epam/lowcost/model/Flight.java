package com.epam.lowcost.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "FLIGHTS")
@Data
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "PLANE_ID")
    private Plane plane;

    @Column(name = "INITIAL_PRICE")
    private long initialPrice;

    @Column(name = "DEPARTURE_DATE")
    private LocalDateTime departureDate;

    @Column(name = "ARRIVAL_DATE")
    private LocalDateTime arrivalDate;

    @Column(name = "DEPARTURE_AIRPORT")
    private String departureAirport;

    @Column(name = "ARRIVAL_AIRPORT")
    private String arrivalAirport;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;

    @Column(name = "PLACE_PRIORITY_PRICE")
    private long placePriorityPrice;

    @Column(name = "BUSINESS_PRICE")
    private long businessPrice;

    @Column(name = "LUGGAGE_PRICE")
    private long luggagePrice;
}

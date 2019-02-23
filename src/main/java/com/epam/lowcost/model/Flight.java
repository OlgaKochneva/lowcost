package com.epam.lowcost.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

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
    @NonNull
    private Plane plane;

    @Column(name = "INITIAL_PRICE")
    @NonNull
    private long initialPrice;

    @Column(name = "DEPARTURE_DATE")
    @NonNull
    private LocalDateTime departureDate;

    @Column(name = "ARRIVAL_DATE")
    @NonNull
    private LocalDateTime arrivalDate;

    @Column(name = "DEPARTURE_AIRPORT")
    @NonNull
    private String departureAirport;

    @Column(name = "ARRIVAL_AIRPORT")
    @NonNull
    private String arrivalAirport;

    @Column(name = "IS_DELETED")
    @NonNull
    private boolean isDeleted;

    @Column(name = "PLACE_PRIORITY_PRICE")
    @NonNull
    private long placePriorityPrice;

    @Column(name = "BUSINESS_PRICE")
    @NonNull
    private long businessPrice;

    @Column(name = "LUGGAGE_PRICE")
    @NonNull
    private long luggagePrice;
}

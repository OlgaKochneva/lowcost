package com.epam.lowcost.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TICKET")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "userId", nullable = false)
    private User user;

    @Column(name = "flightId", nullable = false)
    private Flight flight;

    @Column(name = "isBusiness", nullable = false)
    private boolean isBusiness;

    @Column(name = "hasLuggage", nullable = false)
    private boolean hasLuggage;

    @Column(name = "placePriority", nullable = false)
    private boolean placePriority;

    @Column(name = "price", nullable = false)
    private long price;

    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted;
}

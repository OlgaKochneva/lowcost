package com.epam.lowcost.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TICKETS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "FLIGHT_ID")
    private Flight flight;

    @Column(name = "IS_BUSINESS")
    private boolean isBusiness;

    @Column(name = "HAS_LUGGAGE")
    private boolean hasLuggage;

    @Column(name = "PLACE_PRIORITY")
    private boolean placePriority;

    @Column(name = "PRICE")
    private long price;

    @Column(name = "PURCHASE_DATE")
    private LocalDateTime purchaseDate;

    @Column(name = "IS_DELETED")
    private boolean isDeleted;
}

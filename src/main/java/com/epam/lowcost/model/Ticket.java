package com.epam.lowcost.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

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
    @NonNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "FLIGHT_ID")
    @NonNull
    private Flight flight;

    @Column(name = "IS_BUSINESS")
    @NonNull
    private boolean isBusiness;

    @Column(name = "HAS_LUGGAGE")
    @NonNull
    private boolean hasLuggage;

    @Column(name = "PLACE_PRIORITY")
    @NonNull
    private boolean placePriority;

    @Column(name = "PRICE")
    @NonNull
    private long price;

    @Column(name = "PURCHASE_DATE")
    @NonNull
    private LocalDateTime purchaseDate;

    @Column(name = "IS_DELETED")
    @NonNull
    private boolean isDeleted;
}

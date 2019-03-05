package com.epam.lowcost.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TICKETS")
@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="FLIGHT_ID")
    private Flight flight;

    private boolean business;

    private boolean hasLuggage;

    private boolean placePriority;

    private long price;

    private LocalDateTime purchaseDate;

    private boolean paid;

    private boolean isDeleted;
}

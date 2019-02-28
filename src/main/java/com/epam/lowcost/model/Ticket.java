package com.epam.lowcost.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="FLIGHT_ID")
    private Flight flight;

    private boolean isBusiness;

    private boolean hasLuggage;

    private boolean placePriority;

    private long price;

    private LocalDateTime purchaseDate;

    private boolean isDeleted;
}

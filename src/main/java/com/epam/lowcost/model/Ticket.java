package com.epam.lowcost.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    private long id;
    private User user;
    private Flight flight;
    private boolean isBusiness;
    private boolean hasLuggage;
    private boolean placePriority;
    private long price;
    private boolean isDeleted;
}

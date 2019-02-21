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
public class Plane {
    private long id;
    private String model;
    private int businessPlacesNumber;
    private int economPlacesNumber;
    private boolean isDeleted;
}

package com.epam.lowcost.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "PLANES")
@Data
@Builder
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "MODEL")
    private String model;
    @Column(name = "BUSINESS_PLACES_NUMBER")
    private int businessPlacesNumber;
    @Column(name = "ECONOM_PLACES_NUMBER")
    private int economPlacesNumber;
    @Column(name = "IS_DELETED")
    private Boolean isDeleted;
}

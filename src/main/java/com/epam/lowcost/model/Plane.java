package com.epam.lowcost.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PLANE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "businessPlacesNumber", nullable = false)
    private int businessPlacesNumber;

    @Column(name = "economPlacesNumber", nullable = false)
    private int economPlacesNumber;

    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted;
}

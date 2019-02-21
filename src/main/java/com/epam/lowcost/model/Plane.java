package com.epam.lowcost.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PLANES")
@Data
public class Plane {
    @Id
    private long id;
    @Column(name = "model")
    private String model;
    @Column(name = "businessPlacesNumber")
    private int businessPlacesNumber;
    @Column(name = "economPlacesNumber")
    private int economPlacesNumber;
    @Column(name = "isDeleted")
    private boolean isDeleted;
}

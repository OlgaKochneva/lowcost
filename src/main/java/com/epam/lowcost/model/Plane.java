package com.epam.lowcost.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "PLANES")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "MODEL")
    @NonNull
    private String model;

    @Column(name = "BUSINESS_PLACES_NUMBER")
    @NonNull
    private int businessPlacesNumber;

    @Column(name = "ECONOM_PLACES_NUMBER")
    @NonNull
    private int economPlacesNumber;

    @Column(name = "IS_DELETED")
    @NonNull
    private Boolean isDeleted;
}

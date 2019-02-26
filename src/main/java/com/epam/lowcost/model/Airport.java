package com.epam.lowcost.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AIRPORTS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    @Id
    private String code;
    private String cityEng;
    private String cityRus;
    private String nameEng;
    private String nameRus;
    private String countryEng;
    private String countryRus;
}


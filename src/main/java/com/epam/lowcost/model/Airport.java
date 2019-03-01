package com.epam.lowcost.model;


import lombok.*;

import javax.persistence.*;

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

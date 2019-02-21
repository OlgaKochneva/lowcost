package com.epam.lowcost.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private long id;
    private String email;
    private String password;
    private boolean isAdmin;
    private String firstName;
    private String lastName;
    private String documentInfo;
    private LocalDateTime birthday;
    private boolean isDeleted;
}

package com.epam.lowcost.model;

import java.time.LocalDateTime;


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

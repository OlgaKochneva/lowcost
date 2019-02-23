package com.epam.lowcost.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "EMAIL")
    @NonNull
    private String email;

    @Column(name = "PASSWORD")
    @NonNull
    private String password;

    @Column(name = "IS_ADMIN")
    @NonNull
    private boolean isAdmin;

    @Column(name = "FIRST_NAME")
    @NonNull
    private String firstName;

    @Column(name = "LAST_NAME")
    @NonNull
    private String lastName;

    @Column(name = "DOCUMENT_INFO")
    @NonNull
    private String documentInfo;

    @Column(name = "BIRTHDAY")
    @NonNull
    private LocalDateTime birthday;

    @Column(name = "IS_DELETED")
    @NonNull
    private boolean isDeleted;
}

package com.epam.lowcost.model;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @Column(name = "ID")
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

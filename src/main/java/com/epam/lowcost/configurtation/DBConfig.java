package com.epam.lowcost.configurtation;

import com.epam.lowcost.model.User;
import com.epam.lowcost.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
public class DBConfig extends SpringBootServletInitializer {
    private final UserRepository userRepository;

    @Autowired
    private DBConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    private void init() {
        User user = User.builder()
                .email("EMAIL@MAIL.COM")
                .password("$2a$10$EwF8x4Hj/lYPQx8SN14hSeiGy.uUr6VP0N7Ak6He7tAWtG4IEqCWq")
                .isAdmin(true)
                .firstName("Thomas")
                .lastName("Jefferson")
                .documentInfo("№1234 bestpassport ever")
                .birthday(LocalDateTime.parse("2000-06-05T00:00:00"))
                .isDeleted(false)
                .build();
        userRepository.save(user);
        user = User.builder()
                .email("ExampleEmail2@google.com")
                .password("$2a$10$eYDplzU/lublIM.vu0VBY.v/LIVG8A4I7gDhH1IAp2jgRKP3nL8Pe")
                .isAdmin(false)
                .firstName("John")
                .lastName("Smith")
                .documentInfo("№1234 bestpassport ever")
                .birthday(LocalDateTime.parse("2000-06-04T00:00:00"))
                .isDeleted(false)
                .build();
        userRepository.save(user);
    }
}

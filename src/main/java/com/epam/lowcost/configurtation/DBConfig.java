//package com.epam.lowcost.configurtation;
//
//import com.epam.lowcost.model.Role;
//import com.epam.lowcost.model.User;
//import com.epam.lowcost.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//public class DBConfig extends SpringBootServletInitializer {
//    private final UserRepository userRepository;
//
//    @Autowired
//    private DBConfig(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @PostConstruct
//    private void init() {
//        Set<Role> roles = new HashSet<>();
//        roles.add(Role.ROLE_ADMIN);
//        roles.add(Role.ROLE_USER);
//        User user = User.builder()
//                .username("EMAIL@MAIL.COM")
//                .password("PASSWORD")
//                .active(true)
//                .firstName("Thomas")
//                .lastName("Jefferson")
//                .documentInfo("№1234 bestpassport ever")
//                .birthday(LocalDateTime.parse("2000-06-05T00:00:00"))
//                .roles(roles)
//                .build();
//        userRepository.save(user);
//
//        Set<Role> roles1 = new HashSet<>();
//        roles1.add(Role.ROLE_USER);
//        User user1 = User.builder()
//                .username("ExampleEmail2@google.com")
//                .password("ExamplePassword2")
//                .active(true)
//                .firstName("John")
//                .lastName("Smith")
//                .documentInfo("№1234 bestpassport ever")
//                .birthday(LocalDateTime.parse("2000-06-04T00:00:00"))
//                .roles(roles1)
//                .build();
//        userRepository.save(user1);
//
////        $2a$10$EwF8x4Hj/lYPQx8SN14hSeiGy.uUr6VP0N7Ak6He7tAWtG4IEqCWq - PASSWORD
////        $2a$10$eYDplzU/lublIM.vu0VBY.v/LIVG8A4I7gDhH1IAp2jgRKP3nL8Pe - ExamplePassword2
//    }
//}

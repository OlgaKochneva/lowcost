package com.epam.lowcost.controller;

import com.epam.lowcost.util.Endpoints;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;



@Configuration
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class NavigationController {

    @GetMapping("/users")
    public String usersPage() {
        System.out.println("BAB");
        return "userPage";
    }

}

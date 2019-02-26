package com.epam.lowcost.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import static com.epam.lowcost.util.Endpoints.*;

@Controller
public class LoginController {

    @RequestMapping(value = LOGIN, method = RequestMethod.GET)
    public String showRegistrationPage() {
        return "loginPage";
    }

    @RequestMapping(value = LOGIN, method = RequestMethod.POST)
    public String enter() {
        return "redirect:/";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage() {


        return "searchPage";
    }

    @GetMapping(value = LOG_OUT)
    public String logOut(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "loginPage";
    }

}

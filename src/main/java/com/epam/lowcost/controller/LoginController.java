package com.epam.lowcost.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.epam.lowcost.util.Endpoints.*;

@Controller
@RequestMapping(value = LOGIN)
public class LoginController {

    public String showRegistrationPage() {
        return "login";
    }

    @RequestMapping(value = LOGIN, method = RequestMethod.POST)
    public String enter() {

        return "redirect:/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String mainPage() {
        return "index";
    }
}

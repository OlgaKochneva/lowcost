package com.epam.lowcost.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import static com.epam.lowcost.util.Endpoints.LOGIN;
import static com.epam.lowcost.util.Endpoints.LOG_OUT;

@Controller
public class LoginController {

    @RequestMapping(value = LOGIN, method = RequestMethod.GET)
    public String showRegistrationPage() {
        return "loginPage";
    }

    @RequestMapping(value = LOGIN, method = RequestMethod.POST)
    public String enter(ModelMap model) {

        return "redirect:/";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {

        return "searchPage";
    }

    @GetMapping(value = LOG_OUT)
    public String logOut(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "loginPage";
    }

}

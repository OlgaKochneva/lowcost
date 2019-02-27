package com.epam.lowcost.controller;


import com.epam.lowcost.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import static com.epam.lowcost.util.Endpoints.LOGIN;
import static com.epam.lowcost.util.Endpoints.LOG_OUT;

@Controller
@SessionAttributes(value = "sessionUser")
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping(value = LOGIN, method = RequestMethod.GET)
    public String showRegistrationPage() {
        return "loginPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String enter(ModelMap model) {
        return "redirect:/";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        model.addAttribute("sessionUser", userService.getSessionUser());
        return "searchPage";
    }

    @GetMapping(value = LOG_OUT)
    public String logOut(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/login";
    }

}

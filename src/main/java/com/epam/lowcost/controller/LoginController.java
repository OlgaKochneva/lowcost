package com.epam.lowcost.controller;

import com.epam.lowcost.model.User;
import com.epam.lowcost.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;
import java.util.ResourceBundle;

import static com.epam.lowcost.util.Endpoints.*;


@Controller
@RequestMapping(value = ENTRY)
@SessionAttributes(value = "sessionUser")
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping
    public String auth(@ModelAttribute("sessionUser") User sessionUser) {
        return "search";
    }

    @GetMapping(value = ADMIN_PANEL)
    public String toAdminPanel(@ModelAttribute("sessionUser") User sessionUser) {
        return "admin";
    }


    @GetMapping(value = REGISTRATION)
    public String toRegistrationPage() {
        return "registration";
    }

    @PostMapping
    public String login(@RequestParam Map<String, String> logPass, Model model) {

        ResourceBundle bundle = ResourceBundle.getBundle("lang");

        User user = userService.verifyUser(logPass.get("email"), logPass.get("password"));

        if (user == null) {
            model.addAttribute("message", bundle.getString("lang.W1NoSuchUser"));
        } else {
            model.addAttribute("sessionUser", user);
            return "redirect:" + TICKETS + SELF;
        }
        return "login";
    }

    @GetMapping(value = LOG_OUT)
    public String logOut(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:" + ENTRY;
    }

}

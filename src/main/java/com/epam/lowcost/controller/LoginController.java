package com.epam.lowcost.controller;


import com.epam.lowcost.model.User;
import com.epam.lowcost.services.interfaces.SecurityService;
import com.epam.lowcost.services.interfaces.UserService;
import com.epam.lowcost.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.epam.lowcost.util.Endpoints.*;

@Controller
@SessionAttributes(value = "sessionUser")
public class LoginController {
    private final UserService userService;
    private final SecurityService securityService;
    private final UserValidator userValidator;

    @Autowired
    public LoginController(UserService userService, SecurityService securityService, UserValidator userValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping(REGISTRATION)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return REGISTRATION_PAGE;
    }

    @PostMapping(REGISTRATION)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return REGISTRATION_PAGE;
        }

        userService.addUser(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

    @GetMapping(LOGIN)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid or you have been banned!.");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        model.addAttribute("sessionUser", userService.getSessionUser());
        return LOGIN_PAGE;
    }



    @GetMapping("/")
    public String welcome(Model model) {

        return SEARCH_PAGE;
    }


}

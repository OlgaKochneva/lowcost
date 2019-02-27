//package com.epam.lowcost.controller;
//
//import com.epam.lowcost.services.interfaces.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.Map;
//
//import static com.epam.lowcost.util.Endpoints.REGISTRATION;
//
//@Controller
//public class RegistrationController {
//    private final UserService userService;
//
//    @Autowired
//    public RegistrationController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @RequestMapping(value = REGISTRATION, method = RequestMethod.GET)
//    public String showRegistrationPage() {
//        return "registrationPage";
//    }
//
//    @RequestMapping(value = REGISTRATION, method = RequestMethod.POST)
//    public String processRegistration(@RequestParam Map<String, String> params, Model model) {
//        String response = "loginPage";
//        if (userService.addUser(params)) {
//            model.addAttribute("message", "Successfully registered. Please Log in.");
//        } else {
//            model.addAttribute("message", "User with this email already existed.");
//            response = "registrationPage";
//        }
//        return "loginPage";
//    }
//}

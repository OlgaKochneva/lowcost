package com.epam.lowcost.controller;

import com.epam.lowcost.model.User;
import com.epam.lowcost.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

import static com.epam.lowcost.util.Endpoints.*;

@Controller
@RequestMapping(value = USER)
@SessionAttributes(value = "sessionUser")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping(value = ALL)
    public String getAllUsers(@ModelAttribute(value = "sessionUser") User sessionUser, Model model) {
        if (!sessionUser.isAdmin()) {
            return "redirect:" + TICKETS + SELF;
        }
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping
    public String getById(@ModelAttribute(value = "sessionUser") User sessionUser, @RequestParam long id, Model model) {
        if (!sessionUser.isAdmin()) {
            return "redirect:" + TICKETS + SELF;
        }
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("message", "Here is your User!");
        return "users";
    }

    @PostMapping
    public String addUser(@RequestParam Map<String, String> params, Model model) {
        User user = userService.addUser(
                User.builder()
                        .email(params.get("email"))
                        .password(params.get("password"))
                        .isAdmin(Boolean.valueOf(params.get("isAdmin")))
                        .firstName(params.get("firstName"))
                        .lastName(params.get("lastName"))
                        .documentInfo(params.get("documentInfo"))
                        .birthday(LocalDateTime.parse(params.get("birthday")))
                        .isDeleted(false)
                        .build());
        model.addAttribute("user", user);
        model.addAttribute("message", "User successfully added");
        return "users";
    }

    @PostMapping(value = UPDATE)
    public String updateUser(@RequestParam Map<String, String> params, Model model) {
        User user = userService.updateUser(
                User.builder()
                        .id(Long.valueOf(params.get("id")))
                        .email(params.get("email"))
                        .password(params.get("password"))
                        .isAdmin(Boolean.valueOf(params.get("isAdmin")))
                        .firstName(params.get("firstName"))
                        .lastName(params.get("lastName"))
                        .documentInfo(params.get("documentInfo"))
                        .birthday(LocalDateTime.parse(params.get("birthday")))
                        .build());
        if (user == null) {
            model.addAttribute("message", "No such user or it has been deleted!");
        }
        if (params.get("userUpdate").equals("fromUser")) {
            model.addAttribute("sessionUser", user);
            return "redirect:" + TICKETS + SELF;
        } else {
            model.addAttribute("user", user);
            model.addAttribute("message", "User successfully updated");
        }
        return "users";
    }

    @PostMapping(value = ENROLL)
    public String createUser(@RequestParam Map<String, String> params, Model model) {
        userService.addUser(
                User.builder()
                        .email(params.get("email"))
                        .password(params.get("password"))
                        .isAdmin(Boolean.valueOf(params.get("isAdmin")))
                        .firstName(params.get("firstName"))
                        .lastName(params.get("lastName"))
                        .documentInfo(params.get("documentInfo"))
                        .birthday(LocalDateTime.parse(params.get("birthday")))
                        .isDeleted(false)
                        .build());
        model.addAttribute("message", "Successfully registered. Please Log in. ");
        return "login";

    }

    @GetMapping(value = SETTINGS)
    public String settings(@ModelAttribute("sessionUser") User sessionUser) {
        return "settings";
    }

    @PostMapping(value = CHANGE_PASSWORD)
    public String changePassword(@ModelAttribute("sessionUser") User sessionUser, @RequestParam Map<String, String> params, Model model) {
        User user = userService.verifyUser(sessionUser.getEmail(), params.get("oldPassword"));
        if (user == null) {
            model.addAttribute("message", "Wrong password");
            return "settings";
        }
        if (!params.get("newPassword").equals(params.get("newPassword2"))) {
            model.addAttribute("message", "Passwords did not match!");
            return "settings";
        }
        sessionUser.setPassword(params.get("newPassword"));
        userService.updateUser(sessionUser);
        model.addAttribute("message", "Passwords changed successfully!");
        return "settings";
    }

    @PostMapping(value = DELETE)
    public String deleteUser(@ModelAttribute(value = "sessionUser") User sessionUser, @RequestParam long id, Model model) {
        if (!sessionUser.isAdmin()) {
            return "redirect:" + TICKETS + SELF;
        }
        model.addAttribute("message", userService.deleteUser(id));
        return "users";
    }

}

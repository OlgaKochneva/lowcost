package com.epam.lowcost.controller;

import com.epam.lowcost.model.User;
import com.epam.lowcost.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")

public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "usersPage";
    }

    @RequestMapping(value = "/block-user", method = RequestMethod.POST)
    public String blockUser(@RequestParam long id, Model model, Principal principal) {
        if (principal.getName().equals(userService.getById(id).getUsername())) {
            return "redirect:/users";
        }
        userService.blockUser(id);
        return "redirect:/users";
    }

    @RequestMapping(value = "/unblock-user", method = RequestMethod.POST)
    public String unblockUser(@RequestParam long id, ModelMap model) {
        userService.unblockUser(id);
        return "redirect:/users";
    }

    @RequestMapping(value = "/user/settings", method = RequestMethod.GET)
    public String settings(ModelMap modelMap) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User userToUpdate = userService.findByUsername(auth.getName());
        System.out.println(userToUpdate);
        modelMap.addAttribute("userToUpdate",userToUpdate);

        return "settingsPage";
    }

}

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
import java.time.LocalDate;
import java.util.Map;

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
        modelMap.addAttribute("userToUpdate", userToUpdate);

        return "settingsPage";
    }

    @RequestMapping(value = "/update-user")
    public String updateUser(@RequestParam Map<String, String> params, ModelMap model) {

        User userToUpdate = userService.getById(Long.parseLong(params.get("id")));
        userToUpdate.setUsername(params.get("username"));
        userToUpdate.setFirstName(params.get("firstName"));
        userToUpdate.setLastName(params.get("lastName"));
        userToUpdate.setDocumentInfo(params.get("documentInfo"));
        userToUpdate.setBirthday(LocalDate.parse(params.get("birthday")).atStartOfDay());
        userService.updateUser(userToUpdate);
        return "settingsPage";


    }

}

package com.epam.lowcost.controller;

import com.epam.lowcost.model.User;
import com.epam.lowcost.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Map;

import static com.epam.lowcost.util.Constants.DEFAULT_NUMBER_OF_USERS_ON_PAGE;
import static com.epam.lowcost.util.Endpoints.*;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@SessionAttributes({"sessionUser","number"})
public class UserController {


    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = USER + "/{pageId}", method = RequestMethod.GET)
    public String mainPage(@PathVariable int pageId, ModelMap model) {
        if (pageId <= 0) {
            pageId = 1;
        }
        int usersOnPage = (int) model.getOrDefault("number", DEFAULT_NUMBER_OF_USERS_ON_PAGE);

        String searchTerm = "lastName";
        String searchString = "Petrov";


        Page<User> pageWithUsers = userService.searchByTerm(pageId, searchTerm, searchString, usersOnPage);

        if (pageId >= pageWithUsers.getTotalPages()) {
            pageId = pageWithUsers.getTotalPages() - 1;
        }
        model.addAttribute("pageId", pageId);
        model.addAttribute("pagesNum", String.valueOf(pageWithUsers.getTotalPages()));
        model.addAttribute("users", pageWithUsers.getContent());
        return USERS_PAGE;
    }

    @RequestMapping(value = PAGE, method = RequestMethod.GET)
    public String setUsersByPage(@RequestParam String number, @RequestParam String fromPage, Model model) {
        model.addAttribute("number", Integer.parseInt(number));
        return "redirect:" + fromPage + FIRST_PAGE;
    }

    @RequestMapping(value = BLOCK_USER, method = RequestMethod.POST)
    public String blockUser(@RequestParam long id, Model model, Principal principal) {
        if (principal.getName().equals(userService.getById(id).getUsername())) {
            return "redirect:" + USER;
        }
        userService.blockUser(id);
        return "redirect:" + USER + FIRST_PAGE;
    }

    @RequestMapping(value = UNBLOCK_USER, method = RequestMethod.POST)
    public String unblockUser(@RequestParam long id, ModelMap model) {
        userService.unblockUser(id);
        return "redirect:" + USER + FIRST_PAGE;
    }

    @RequestMapping(value = USER_SETTINGS, method = RequestMethod.GET)
    public String settings(ModelMap model) {

        model.addAttribute("sessionUser", userService.getSessionUser());

        return SETTINGS_PAGE;
    }

    @RequestMapping(value = UPDATE_USER)
    public String updateUser(@RequestParam Map<String, String> params, ModelMap model) {

        User userToUpdate = userService.getById(Long.parseLong(params.get("id")));
        userToUpdate.setUsername(params.get("username"));
        userToUpdate.setFirstName(params.get("firstName"));
        userToUpdate.setLastName(params.get("lastName"));
        userToUpdate.setDocumentInfo(params.get("documentInfo"));
        userToUpdate.setBirthday(LocalDate.parse(params.get("birthday")).atStartOfDay());
        userService.updateUser(userToUpdate);
        return "redirect:" + USER_SETTINGS;
    }

    @RequestMapping(value = CHANGE_PASSWORD)
    public String changePassword(@RequestParam Map<String, String> params, Model model) {
        User userToUpdate = userService.getById(Long.parseLong(params.get("id")));

        if (!bCryptPasswordEncoder.matches(params.get("oldPassword"), userToUpdate.getPassword())) {
            model.addAttribute("message", "Wrong password!");
            return "redirect:" + USER_SETTINGS;
        }
        if (!params.get("newPassword").equals(params.get("newPassword2"))) {
            model.addAttribute("message", "Passwords do not match!");
            return "redirect:" + USER_SETTINGS;
        }
        userToUpdate.setPassword(bCryptPasswordEncoder.encode(params.get("newPassword")));
        userService.updateUser(userToUpdate);
        return "redirect:" + USER_SETTINGS;
    }

}

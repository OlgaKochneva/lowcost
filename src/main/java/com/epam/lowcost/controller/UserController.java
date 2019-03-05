package com.epam.lowcost.controller;

import com.epam.lowcost.model.User;
import com.epam.lowcost.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Map;

import static com.epam.lowcost.util.Endpoints.*;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@SessionAttributes({"sessionUser", "searchTerm", "searchString"})
public class UserController {


    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = USERS)
    public String showUsers(ModelMap model, Pageable pageable) {
        String searchTerm = (String) model.getOrDefault("searchTerm", "all");
        String searchString = (String) model.getOrDefault("searchString", "not-relevant");
        model.addAttribute("users", userService.searchByTerm(searchTerm, searchString, pageable));
        return USERS_PAGE;
    }

    @RequestMapping(value = SEARCH, method = RequestMethod.POST)
    public String setSearchConditions(@RequestParam(value = "searchTerm") String searchTerm,
                                      @RequestParam(value = "searchString") String searchString,
                                      ModelMap model) {
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("searchString", searchString);

        return "redirect:" + USERS;
    }


    @RequestMapping(value = BLOCK_USER, method = RequestMethod.POST)
    public String blockUser(@RequestParam long id, Model model, Principal principal) {
        if (principal.getName().equals(userService.getById(id).getUsername())) {
            return "redirect:" + USERS;
        }
        userService.blockUser(id);
        return "redirect:" + USERS;
    }

    @RequestMapping(value = UNBLOCK_USER, method = RequestMethod.POST)
    public String unblockUser(@RequestParam long id, ModelMap model) {
        userService.unblockUser(id);
        return "redirect:" + USERS;
    }

    @RequestMapping(value = USER_SETTINGS, method = RequestMethod.GET)
    public String settings(ModelMap model) {

        model.addAttribute("sessionUser", userService.getSessionUser());

        return SETTINGS_PAGE;
    }
    @RequestMapping(value = USER +"/{user}", method = RequestMethod.GET)
    public String updatePlanePage(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        return "userSettings";
    }

    @RequestMapping(value = UPDATE_USER)
    public String updateUser(@RequestParam Map<String, String> params, ModelMap model) {

        User userToUpdate = userService.getById(Long.parseLong(params.get("id")));
        userToUpdate.setUsername(params.get("username"));
        userToUpdate.setFirstName(params.get("firstName"));
        userToUpdate.setLastName(params.get("lastName"));
        userToUpdate.setDocumentInfo(params.get("documentInfo"));
        userToUpdate.setBirthday(LocalDate.parse(params.get("birthday")).atStartOfDay());
        if("admin".equals(model.get("fromAdmin"))){

            userToUpdate.setPassword(params.get("password"));
            return "redirect:" + USERS;
        }
        model.addAttribute("sessionUser",userService.updateUser(userToUpdate));
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

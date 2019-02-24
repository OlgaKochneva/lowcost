package com.epam.lowcost.services.interfaces;

import com.epam.lowcost.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    User getById(long userId);

    Boolean addUser(Map<String, String> params);

    User updateUser(User user);

    String deleteUser(long userId);

    User verifyUser(String log, String pass);

}

package com.epam.lowcost.services.interfaces;

import com.epam.lowcost.model.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    List<User> getAllUsers();

    User getById(long userId);

    void addUser(User user);

    User updateUser(User user);

    String deleteUser(long userId);

    User verifyUser(String log, String pass);

}

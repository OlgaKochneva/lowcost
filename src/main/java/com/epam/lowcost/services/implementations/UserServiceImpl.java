package com.epam.lowcost.services.implementations;

import com.epam.lowcost.model.Role;
import com.epam.lowcost.model.User;
import com.epam.lowcost.repositories.UserRepository;
import com.epam.lowcost.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getById(long userId) {
        return null;
    }

    @Override
    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<Role>(){{add(Role.ROLE_USER);}});
        userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public String deleteUser(long userId) {
        return null;
    }

    @Override
    public User verifyUser(String log, String pass) {
        return null;
    }

    private User userBuilder(Map<String, String> params) {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        User user = User.builder()
                .username(params.get("email"))
                .password(params.get("password"))
                .active(true)
                .roles(roles)
                .firstName(params.get("firstName"))
                .lastName(params.get("lastName"))
                .documentInfo(params.get("documentInfo"))
                .birthday(LocalDateTime.parse(params.get("birthday")))
                .build();
        return user;
    }
}

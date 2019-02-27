package com.epam.lowcost.services.implementations;

import com.epam.lowcost.model.Role;
import com.epam.lowcost.model.User;
import com.epam.lowcost.repositories.UserRepository;
import com.epam.lowcost.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getById(long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Boolean addUser(Map<String, String> params) {
        Boolean response = false;
        User user = userBuilder(params);
        if (!userRepository.existsByUsername(user.getUsername())) {
            userRepository.save(user);
            response = true;
        }
        return response;
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public String blockUser(long userId) {
        User userToBlock = userRepository.findById(userId);
        userToBlock.setActive(false);
        userRepository.save(userToBlock);
        return "User blocked successfully";
    }
    @Override
    public String unblockUser(long userId) {
        User userToBlock = userRepository.findById(userId);
        userToBlock.setActive(true);
        userRepository.save(userToBlock);
        return "User unblocked successfully";
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

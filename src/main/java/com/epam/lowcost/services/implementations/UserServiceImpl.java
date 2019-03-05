package com.epam.lowcost.services.implementations;

import com.epam.lowcost.model.Role;
import com.epam.lowcost.model.User;
import com.epam.lowcost.repositories.UserRepository;
import com.epam.lowcost.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
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
    public User getSessionUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return this.findByUsername(auth.getName());
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> searchByTerm(String searchTerm, String searchString,Pageable pageable) {

        Page<User> pageWithUsers;

        switch (searchTerm) {
            case "username":
                pageWithUsers = userRepository.findAllByUsernameContains(searchString.trim(), pageable);
                break;
            case "lastName":
                pageWithUsers = userRepository.findAllByLastNameContains(searchString.trim(), pageable);
                break;
            case "documentInfo":
                pageWithUsers = userRepository.findAllByDocumentInfoContains(searchString.trim(), pageable);
                break;
            default:
                pageWithUsers = this.getAllUsers(pageable);
        }

        return pageWithUsers;
    }

    @Override
    public User getById(long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void addUser(User user) {
        user.setActive(true);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<Role>() {{
            add(Role.ROLE_USER);
        }});
        userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User blockUser(long userId) {
        User userToBlock = userRepository.findById(userId);
        userToBlock.setActive(false);
        return userRepository.save(userToBlock);
    }

    @Override
    public User unblockUser(long userId) {
        User userToBlock = userRepository.findById(userId);
        userToBlock.setActive(true);
        return userRepository.save(userToBlock);
    }

}

package com.epam.lowcost.services.implementations;

import com.epam.lowcost.model.Role;
import com.epam.lowcost.model.User;
import com.epam.lowcost.repositories.UserRepository;
import com.epam.lowcost.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.userRepository = userRepository;
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
//                .password(bCryptPasswordEncoder.encode(params.get("password")))
                .active(true)
                .roles(roles)
                .firstName(params.get("firstName"))
                .lastName(params.get("lastName"))
                .documentInfo(params.get("documentInfo"))
                .birthday(LocalDateTime.parse(params.get("birthday")))
                .build();
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}

package com.epam.lowcost.services.implementations;

import com.epam.lowcost.model.User;
import com.epam.lowcost.repositories.UserRepository;
import com.epam.lowcost.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
        if (!userRepository.existsByEmail(user.getEmail())) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
        return User.builder()
                .email(params.get("email"))
                .password(params.get("password"))
                .isAdmin(Boolean.valueOf(params.get("isAdmin")))
                .firstName(params.get("firstName"))
                .lastName(params.get("lastName"))
                .documentInfo(params.get("documentInfo"))
                .birthday(LocalDateTime.parse(params.get("birthday")))
                .isDeleted(Boolean.valueOf(params.get("isDeleted")))
                .build();
    }
}

package com.epam.lowcost.services.interfaces;

import com.epam.lowcost.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User findByUsername(String username);

    Page<User> getAllUsers(Pageable pageable);

    /*Page<User> searchByTerm(Integer pageId, String searchTerm, String searchString, int usersOnPage);*/

    User getById(long userId);

    void addUser(User user);

    User updateUser(User user);

    String blockUser(long userId);

    String unblockUser(long userId);

    User getSessionUser();
}

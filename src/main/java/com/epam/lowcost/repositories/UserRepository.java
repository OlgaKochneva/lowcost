package com.epam.lowcost.repositories;

import com.epam.lowcost.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsername(String username);

    User findByUsername(String username);

    List<User> findAll();

    User findById(long id);

    User save(User user);
}

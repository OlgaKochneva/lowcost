package com.epam.lowcost.repositories;

import com.epam.lowcost.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsername(String username);
    User findByUsername(String username);
    Page<User> findAll(Pageable pageable);
    User findById(long id);
    User save(User user);
}

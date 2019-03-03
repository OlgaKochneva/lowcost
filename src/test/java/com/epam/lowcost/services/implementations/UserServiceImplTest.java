package com.epam.lowcost.services.implementations;

import com.epam.lowcost.model.Role;
import com.epam.lowcost.model.User;
import com.epam.lowcost.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;


    @Test
    public void findByUsername() {

        userService.findByUsername("username");
        verify(userRepository).findByUsername("username");

    }

    @Test
    public void getAllUsers() {
        Pageable mockPage = PageRequest.of(1, 10, Sort.Direction.ASC, "id");
        userService.getAllUsers(mockPage);
        verify(userRepository).findAll(mockPage);

    }

    @Test
    public void getById() {
        userService.getById(1);
        verify(userRepository).findById(1);
    }

    @Test
    public void addUser() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        User expectedUser = new User() {{
            setRoles(roles);
            setActive(true);
        }};
        User actualUser = new User();
        when(userRepository.save(actualUser)).thenReturn(actualUser);
        userService.addUser(actualUser);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void updateUser() {
        User user = new User();
        userService.updateUser(user);
        verify(userRepository).save(user);
    }

    @Test
    public void blockUser() {
        User actualUser = new User();
        User expectedUser = new User() {{
            setActive(false);
        }};
        when(userRepository.findById((long) 1)).thenReturn(actualUser);
        when(userRepository.save(actualUser)).thenReturn(actualUser);
        userService.blockUser(1);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void unblockUser() {
        User actualUser = new User() {{
            setActive(false);
        }};
        User expectedUser = new User() {{
            setActive(true);
        }};
        when(userRepository.findById((long) 1)).thenReturn(actualUser);
        when(userRepository.save(actualUser)).thenReturn(actualUser);
        userService.unblockUser(1);
        assertEquals(expectedUser, actualUser);
    }
}
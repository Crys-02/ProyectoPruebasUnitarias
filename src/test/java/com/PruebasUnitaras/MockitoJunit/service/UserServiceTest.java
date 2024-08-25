package com.PruebasUnitaras.MockitoJunit.service;

import com.PruebasUnitaras.MockitoJunit.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;
    private User user;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        user = new User();
        user.setId(1L);
        user.setName("Gracia Cox");
        user.setEmail("coxgracia@gmail.com");
        userService.saveUser(user);
    }

    @Test
    void testSaveUser() {
        User newUser = new User();
        newUser.setId(2L);
        newUser.setName("Gracia Cox");
        newUser.setEmail("coxgracia@gmail.com");

        User savedUser = userService.saveUser(newUser);

        assertNotNull(savedUser);
        assertEquals(newUser.getId(), savedUser.getId());
        assertEquals("Gracia Cox", savedUser.getName());
    }

    @Test
    void testFindUserById() {
        Optional<User> foundUser = userService.findUserById(1L);

        assertTrue(foundUser.isPresent());
        assertEquals(user.getName(), foundUser.get().getName());
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1L);
        Optional<User> foundUser = userService.findUserById(1L);

        assertFalse(foundUser.isPresent());
    }

    @Test
    void testUpdateUser() {
        User updatedUser = new User();
        updatedUser.setName("Gracia Cox");
        updatedUser.setEmail("coxgracia@gmail.com");

        User result = userService.updateUser(1L, updatedUser);

        assertNotNull(result);
        assertEquals("Gracia Cox", result.getName());
        assertEquals("coxgracia@gmail.com", result.getEmail());
    }

    @Test
    void testFindAllUsers() {
        User anotherUser = new User();
        anotherUser.setId(2L);
        anotherUser.setName("Gracia Cox");
        anotherUser.setEmail("coxgracia@gmail.com");
        userService.saveUser(anotherUser);

        List<User> users = userService.findAllUsers();

        assertEquals(2, users.size());
    }
}

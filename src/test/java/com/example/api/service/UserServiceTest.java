package com.example.api.service;

import com.example.api.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for UserService
 * 
 * Tests all business logic methods
 * 
 * @author Your Name
 * @version 1.0.0
 */
class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        // Create a fresh service instance for each test
        userService = new UserService();
    }

    @Test
    void getAllUsers_ShouldReturnInitialUsers() {
        // Act
        List<User> users = userService.getAllUsers();

        // Assert
        assertNotNull(users);
        assertEquals(3, users.size());
        assertTrue(users.stream().anyMatch(u -> u.getUsername().equals("johndoe")));
        assertTrue(users.stream().anyMatch(u -> u.getUsername().equals("janedoe")));
        assertTrue(users.stream().anyMatch(u -> u.getUsername().equals("bobsmith")));
    }

    @Test
    void getUserById_WhenUserExists_ShouldReturnUser() {
        // Act
        Optional<User> user = userService.getUserById(1L);

        // Assert
        assertTrue(user.isPresent());
        assertEquals("johndoe", user.get().getUsername());
        assertEquals("john.doe@example.com", user.get().getEmail());
    }

    @Test
    void getUserById_WhenUserDoesNotExist_ShouldReturnEmpty() {
        // Act
        Optional<User> user = userService.getUserById(999L);

        // Assert
        assertFalse(user.isPresent());
    }

    @Test
    void getUserByUsername_WhenUserExists_ShouldReturnUser() {
        // Act
        Optional<User> user = userService.getUserByUsername("johndoe");

        // Assert
        assertTrue(user.isPresent());
        assertEquals(1L, user.get().getId());
        assertEquals("John", user.get().getFirstName());
    }

    @Test
    void getUserByUsername_WhenUserDoesNotExist_ShouldReturnEmpty() {
        // Act
        Optional<User> user = userService.getUserByUsername("nonexistent");

        // Assert
        assertFalse(user.isPresent());
    }

    @Test
    void createUser_ShouldAssignIdAndAddToList() {
        // Arrange
        User newUser = new User(null, "alice", "alice@example.com", "Alice", "Wonder");

        // Act
        User createdUser = userService.createUser(newUser);

        // Assert
        assertNotNull(createdUser.getId());
        assertEquals("alice", createdUser.getUsername());
        assertEquals(4, userService.getUserCount());
        assertTrue(createdUser.isActive());
    }

    @Test
    void updateUser_WhenUserExists_ShouldUpdateAndReturnUser() {
        // Arrange
        User updatedData = new User(null, "johndoe_updated", "john.updated@example.com", "John", "Doe Updated");

        // Act
        Optional<User> result = userService.updateUser(1L, updatedData);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("johndoe_updated", result.get().getUsername());
        assertEquals("john.updated@example.com", result.get().getEmail());
        assertEquals("Doe Updated", result.get().getLastName());
    }

    @Test
    void updateUser_WhenUserDoesNotExist_ShouldReturnEmpty() {
        // Arrange
        User updatedData = new User(null, "test", "test@example.com", "Test", "User");

        // Act
        Optional<User> result = userService.updateUser(999L, updatedData);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void deleteUser_WhenUserExists_ShouldReturnTrueAndRemoveUser() {
        // Act
        boolean deleted = userService.deleteUser(1L);

        // Assert
        assertTrue(deleted);
        assertEquals(2, userService.getUserCount());
        assertFalse(userService.getUserById(1L).isPresent());
    }

    @Test
    void deleteUser_WhenUserDoesNotExist_ShouldReturnFalse() {
        // Act
        boolean deleted = userService.deleteUser(999L);

        // Assert
        assertFalse(deleted);
        assertEquals(3, userService.getUserCount());
    }

    @Test
    void deactivateUser_WhenUserExists_ShouldSetActiveToFalse() {
        // Act
        Optional<User> result = userService.deactivateUser(1L);

        // Assert
        assertTrue(result.isPresent());
        assertFalse(result.get().isActive());
        assertEquals(2, userService.getActiveUserCount());
    }

    @Test
    void deactivateUser_WhenUserDoesNotExist_ShouldReturnEmpty() {
        // Act
        Optional<User> result = userService.deactivateUser(999L);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void searchUsersByName_ShouldReturnMatchingUsers() {
        // Act
        List<User> results = userService.searchUsersByName("John");

        // Assert
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("johndoe", results.get(0).getUsername());
    }

    @Test
    void searchUsersByName_WithPartialMatch_ShouldReturnMatches() {
        // Act
        List<User> results = userService.searchUsersByName("Doe");

        // Assert
        assertEquals(2, results.size());
        assertTrue(results.stream().anyMatch(u -> u.getUsername().equals("johndoe")));
        assertTrue(results.stream().anyMatch(u -> u.getUsername().equals("janedoe")));
    }

    @Test
    void searchUsersByName_WithNoMatch_ShouldReturnEmptyList() {
        // Act
        List<User> results = userService.searchUsersByName("Nonexistent");

        // Assert
        assertNotNull(results);
        assertTrue(results.isEmpty());
    }

    @Test
    void getActiveUsers_ShouldReturnOnlyActiveUsers() {
        // Arrange
        userService.deactivateUser(1L);

        // Act
        List<User> activeUsers = userService.getActiveUsers();

        // Assert
        assertEquals(2, activeUsers.size());
        assertTrue(activeUsers.stream().allMatch(User::isActive));
    }

    @Test
    void getUserCount_ShouldReturnCorrectCount() {
        // Act
        long count = userService.getUserCount();

        // Assert
        assertEquals(3, count);
    }

    @Test
    void getActiveUserCount_ShouldReturnCorrectCount() {
        // Arrange
        userService.deactivateUser(1L);
        userService.deactivateUser(2L);

        // Act
        long activeCount = userService.getActiveUserCount();

        // Assert
        assertEquals(1, activeCount);
    }
}

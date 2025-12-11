package com.example.api.controller;

import com.example.api.model.User;
import com.example.api.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for UserController
 * 
 * Tests all REST endpoints with mocked service layer
 * 
 * @author Your Name
 * @version 1.0.0
 */
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;
    private List<User> testUsers;

    @BeforeEach
    void setUp() {
        // Setup test data
        testUser = new User(1L, "johndoe", "john@example.com", "John", "Doe");
        
        User user2 = new User(2L, "janedoe", "jane@example.com", "Jane", "Doe");
        User user3 = new User(3L, "bobsmith", "bob@example.com", "Bob", "Smith");
        
        testUsers = Arrays.asList(testUser, user2, user3);
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers() throws Exception {
        // Arrange
        when(userService.getAllUsers()).thenReturn(testUsers);

        // Act & Assert
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].username").value("johndoe"))
                .andExpect(jsonPath("$[1].username").value("janedoe"))
                .andExpect(jsonPath("$[2].username").value("bobsmith"));
    }

    @Test
    void getUserById_WhenUserExists_ShouldReturnUser() throws Exception {
        // Arrange
        when(userService.getUserById(1L)).thenReturn(Optional.of(testUser));

        // Act & Assert
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("johndoe"))
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    void getUserById_WhenUserDoesNotExist_ShouldReturn404() throws Exception {
        // Arrange
        when(userService.getUserById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/users/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createUser_WithValidData_ShouldReturnCreatedUser() throws Exception {
        // Arrange
        User newUser = new User(null, "newuser", "new@example.com", "New", "User");
        User createdUser = new User(4L, "newuser", "new@example.com", "New", "User");
        
        when(userService.createUser(any(User.class))).thenReturn(createdUser);

        // Act & Assert
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.username").value("newuser"))
                .andExpect(jsonPath("$.email").value("new@example.com"));
    }

    @Test
    void createUser_WithInvalidEmail_ShouldReturn400() throws Exception {
        // Arrange
        User invalidUser = new User(null, "test", "invalid-email", "Test", "User");

        // Act & Assert
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidUser)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateUser_WhenUserExists_ShouldReturnUpdatedUser() throws Exception {
        // Arrange
        User updatedUser = new User(1L, "johndoe_updated", "john.updated@example.com", "John", "Doe");
        when(userService.updateUser(eq(1L), any(User.class))).thenReturn(Optional.of(updatedUser));

        // Act & Assert
        mockMvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("johndoe_updated"))
                .andExpect(jsonPath("$.email").value("john.updated@example.com"));
    }

    @Test
    void updateUser_WhenUserDoesNotExist_ShouldReturn404() throws Exception {
        // Arrange
        User updatedUser = new User(999L, "test", "test@example.com", "Test", "User");
        when(userService.updateUser(eq(999L), any(User.class))).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(put("/users/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteUser_WhenUserExists_ShouldReturn204() throws Exception {
        // Arrange
        when(userService.deleteUser(1L)).thenReturn(true);

        // Act & Assert
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteUser_WhenUserDoesNotExist_ShouldReturn404() throws Exception {
        // Arrange
        when(userService.deleteUser(999L)).thenReturn(false);

        // Act & Assert
        mockMvc.perform(delete("/users/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getActiveUsers_ShouldReturnOnlyActiveUsers() throws Exception {
        // Arrange
        List<User> activeUsers = Arrays.asList(testUser);
        when(userService.getActiveUsers()).thenReturn(activeUsers);

        // Act & Assert
        mockMvc.perform(get("/users/active"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].active").value(true));
    }

    @Test
    void searchUsers_ShouldReturnMatchingUsers() throws Exception {
        // Arrange
        List<User> searchResults = Arrays.asList(testUser);
        when(userService.searchUsersByName("John")).thenReturn(searchResults);

        // Act & Assert
        mockMvc.perform(get("/users/search").param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void getUserStats_ShouldReturnStatistics() throws Exception {
        // Arrange
        when(userService.getUserCount()).thenReturn(3L);
        when(userService.getActiveUserCount()).thenReturn(3L);

        // Act & Assert
        mockMvc.perform(get("/users/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalUsers").value(3))
                .andExpect(jsonPath("$.activeUsers").value(3))
                .andExpect(jsonPath("$.inactiveUsers").value(0));
    }
}

package com.example.api.controller;

import com.example.api.model.User;
import com.example.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;

/**
 * User Controller - REST API endpoints for user management
 * 
 * Provides RESTful endpoints for CRUD operations on users.
 * Base URL: http://localhost:8080/api/users
 * 
 * @author Your Name
 * @version 1.0.0
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get all users
     * 
     * @return List of all users
     * @endpoint GET /api/users
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Get active users only
     * 
     * @return List of active users
     * @endpoint GET /api/users/active
     */
    @GetMapping("/active")
    public ResponseEntity<List<User>> getActiveUsers() {
        List<User> users = userService.getActiveUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Get user by ID
     * 
     * @param id User ID
     * @return User if found, 404 if not found
     * @endpoint GET /api/users/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new user
     * 
     * @param user User object from request body
     * @return Created user with 201 status
     * @endpoint POST /api/users
     */
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    /**
     * Update an existing user
     * 
     * @param id User ID to update
     * @param user Updated user data
     * @return Updated user if found, 404 if not found
     * @endpoint PUT /api/users/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        Optional<User> updatedUser = userService.updateUser(id, user);
        return updatedUser.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete a user
     * 
     * @param id User ID to delete
     * @return 204 No Content if deleted, 404 if not found
     * @endpoint DELETE /api/users/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        return deleted ? ResponseEntity.noContent().build() 
                      : ResponseEntity.notFound().build();
    }

    /**
     * Deactivate a user (soft delete)
     * 
     * @param id User ID to deactivate
     * @return Deactivated user if found, 404 if not found
     * @endpoint PATCH /api/users/{id}/deactivate
     */
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<User> deactivateUser(@PathVariable Long id) {
        Optional<User> user = userService.deactivateUser(id);
        return user.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Search users by name
     * 
     * @param name Search term for first or last name
     * @return List of matching users
     * @endpoint GET /api/users/search?name={name}
     */
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String name) {
        List<User> users = userService.searchUsersByName(name);
        return ResponseEntity.ok(users);
    }

    /**
     * Get user statistics
     * 
     * @return Statistics about users
     * @endpoint GET /api/users/stats
     */
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getUserStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", userService.getUserCount());
        stats.put("activeUsers", userService.getActiveUserCount());
        stats.put("inactiveUsers", userService.getUserCount() - userService.getActiveUserCount());
        return ResponseEntity.ok(stats);
    }
}

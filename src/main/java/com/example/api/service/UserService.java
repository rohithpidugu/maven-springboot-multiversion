package com.example.api.service;

import com.example.api.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * User Service - Business logic for user operations
 * 
 * This service handles all business logic related to users.
 * Uses in-memory storage for demonstration purposes.
 * 
 * @author Your Name
 * @version 1.0.0
 */
@Service
public class UserService {

    // In-memory storage (simulating a database)
    private final List<User> users = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    /**
     * Constructor - Initialize with sample data
     */
    public UserService() {
        // Add some sample users
        createUser(new User(null, "johndoe", "john.doe@example.com", "John", "Doe"));
        createUser(new User(null, "janedoe", "jane.doe@example.com", "Jane", "Doe"));
        createUser(new User(null, "bobsmith", "bob.smith@example.com", "Bob", "Smith"));
    }

    /**
     * Get all users
     * 
     * @return List of all users
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    /**
     * Get all active users
     * 
     * @return List of active users
     */
    public List<User> getActiveUsers() {
        return users.stream()
                .filter(User::isActive)
                .collect(Collectors.toList());
    }

    /**
     * Get user by ID
     * 
     * @param id User ID
     * @return Optional containing user if found
     */
    public Optional<User> getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    /**
     * Get user by username
     * 
     * @param username Username to search for
     * @return Optional containing user if found
     */
    public Optional<User> getUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    /**
     * Create a new user
     * 
     * @param user User object to create
     * @return Created user with generated ID
     */
    public User createUser(User user) {
        // Assign new ID
        user.setId(idCounter.getAndIncrement());
        users.add(user);
        return user;
    }

    /**
     * Update an existing user
     * 
     * @param id User ID to update
     * @param updatedUser User object with updated data
     * @return Optional containing updated user if found
     */
    public Optional<User> updateUser(Long id, User updatedUser) {
        Optional<User> existingUser = getUserById(id);
        
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setActive(updatedUser.isActive());
            return Optional.of(user);
        }
        
        return Optional.empty();
    }

    /**
     * Delete a user
     * 
     * @param id User ID to delete
     * @return true if user was deleted, false if not found
     */
    public boolean deleteUser(Long id) {
        return users.removeIf(user -> user.getId().equals(id));
    }

    /**
     * Deactivate a user (soft delete)
     * 
     * @param id User ID to deactivate
     * @return Optional containing deactivated user if found
     */
    public Optional<User> deactivateUser(Long id) {
        Optional<User> user = getUserById(id);
        user.ifPresent(u -> u.setActive(false));
        return user;
    }

    /**
     * Search users by name
     * 
     * @param searchTerm Search term to match against first or last name
     * @return List of matching users
     */
    public List<User> searchUsersByName(String searchTerm) {
        String lowerSearch = searchTerm.toLowerCase();
        return users.stream()
                .filter(user -> 
                    user.getFirstName().toLowerCase().contains(lowerSearch) ||
                    user.getLastName().toLowerCase().contains(lowerSearch))
                .collect(Collectors.toList());
    }

    /**
     * Get total user count
     * 
     * @return Total number of users
     */
    public long getUserCount() {
        return users.size();
    }

    /**
     * Get active user count
     * 
     * @return Number of active users
     */
    public long getActiveUserCount() {
        return users.stream().filter(User::isActive).count();
    }
}

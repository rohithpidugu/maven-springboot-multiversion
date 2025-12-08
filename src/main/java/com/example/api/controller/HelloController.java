package com.example.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello Controller - Basic REST endpoint for testing
 * 
 * This controller provides simple endpoints to verify the
 * application is running correctly and accessible.
 * 
 * Base URL: http://localhost:8080/api/hello
 * 
 * @author Your Name
 * @version 1.0.0
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    /**
     * Simple hello endpoint
     * 
     * @return Welcome message
     */
    @GetMapping
    public Map<String, String> sayHello() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello from Maven Spring Boot Multi-Version API!");
        response.put("status", "success");
        return response;
    }

    /**
     * Health check endpoint
     * 
     * @return Application health status
     */
    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("application", "maven-springboot-multiversion");
        response.put("javaVersion", System.getProperty("java.version"));
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }
}
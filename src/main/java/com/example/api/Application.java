package com.example.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Application class for Spring Boot REST API
 * 
 * This is the entry point of the application that initializes
 * the Spring Boot context and starts the embedded Tomcat server.
 * 
 * @SpringBootApplication annotation enables:
 * - @Configuration: Tags the class as a source of bean definitions
 * - @EnableAutoConfiguration: Enables Spring Boot's auto-configuration
 * - @ComponentScan: Enables component scanning in the current package
 * 
 * @author Your Name
 * @version 1.0.0
 */
@SpringBootApplication
public class Application {

    /**
     * Main method - Entry point for the Spring Boot application
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
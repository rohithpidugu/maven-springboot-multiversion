# API Usage Examples

This document provides practical examples for using the Maven Spring Boot Multi-Version API.

## 🚀 Quick Start Examples

### 1. Basic Health Check

**Request:**
```bash
curl http://localhost:8080/api/hello/health
```

**Expected Response:**
```json
{
  "status": "UP",
  "application": "maven-springboot-multiversion",
  "javaVersion": "11.0.29",
  "timestamp": 1702329600000
}
```

**Use Case:** Verify the application is running and check which Java version is being used.

---

## 👥 User Management Examples

### 2. View All Users

**Request:**
```bash
curl http://localhost:8080/api/users
```

**Response:**
```json
[
  {
    "id": 1,
    "username": "johndoe",
    "email": "john.doe@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "active": true
  },
  {
    "id": 2,
    "username": "janedoe",
    "email": "jane.doe@example.com",
    "firstName": "Jane",
    "lastName": "Doe",
    "active": true
  },
  {
    "id": 3,
    "username": "bobsmith",
    "email": "bob.smith@example.com",
    "firstName": "Bob",
    "lastName": "Smith",
    "active": true
  }
]
```

**Use Case:** Get a list of all users in the system.

---

### 3. Create a New User

**Request:**
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "alice",
    "email": "alice.wonder@example.com",
    "firstName": "Alice",
    "lastName": "Wonder"
  }'
```

**Success Response (201 Created):**
```json
{
  "id": 4,
  "username": "alice",
  "email": "alice.wonder@example.com",
  "firstName": "Alice",
  "lastName": "Wonder",
  "active": true
}
```

**Use Case:** Register a new user in the system.

---

### 4. Create User with Validation Error

**Request (Invalid Email):**
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "bob",
    "email": "invalid-email",
    "firstName": "Bo",
    "lastName": "B"
  }'
```

**Error Response (400 Bad Request):**
```json
{
  "timestamp": "2024-12-10T12:00:00.123",
  "status": 400,
  "error": "Validation Failed",
  "message": "Invalid input data",
  "validationErrors": {
    "email": "Email must be valid",
    "firstName": "First name must be between 2 and 50 characters",
    "lastName": "Last name must be between 2 and 50 characters"
  },
  "path": "/api/users"
}
```

**Use Case:** Demonstrates validation rules enforcement.

---

### 5. Find Specific User

**Request:**
```bash
curl http://localhost:8080/api/users/1
```

**Success Response (200 OK):**
```json
{
  "id": 1,
  "username": "johndoe",
  "email": "john.doe@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "active": true
}
```

**Not Found Response (404):**
```bash
curl http://localhost:8080/api/users/999
# Returns 404 Not Found
```

**Use Case:** Retrieve detailed information about a specific user.

---

### 6. Update User Information

**Request:**
```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "username": "johndoe_updated",
    "email": "john.updated@example.com",
    "firstName": "John",
    "lastName": "Doe Updated"
  }'
```

**Success Response (200 OK):**
```json
{
  "id": 1,
  "username": "johndoe_updated",
  "email": "john.updated@example.com",
  "firstName": "John",
  "lastName": "Doe Updated",
  "active": true
}
```

**Use Case:** Modify existing user details.

---

### 7. Search Users by Name

**Request:**
```bash
curl "http://localhost:8080/api/users/search?name=John"
```

**Response:**
```json
[
  {
    "id": 1,
    "username": "johndoe",
    "email": "john.doe@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "active": true
  }
]
```

**Partial Match Example:**
```bash
curl "http://localhost:8080/api/users/search?name=Doe"
# Returns both John Doe and Jane Doe
```

**Use Case:** Find users by first or last name (case-insensitive partial match).

---

### 8. Get Active Users Only

**Request:**
```bash
curl http://localhost:8080/api/users/active
```

**Response:**
```json
[
  {
    "id": 1,
    "username": "johndoe",
    "email": "john.doe@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "active": true
  },
  {
    "id": 2,
    "username": "janedoe",
    "email": "jane.doe@example.com",
    "firstName": "Jane",
    "lastName": "Doe",
    "active": true
  }
]
```

**Use Case:** Filter out deactivated users from the list.

---

### 9. Deactivate User (Soft Delete)

**Request:**
```bash
curl -X PATCH http://localhost:8080/api/users/1/deactivate
```

**Response:**
```json
{
  "id": 1,
  "username": "johndoe",
  "email": "john.doe@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "active": false
}
```

**Use Case:** Deactivate user without permanently deleting their data.

---

### 10. Delete User (Hard Delete)

**Request:**
```bash
curl -X DELETE http://localhost:8080/api/users/1
```

**Success Response (204 No Content):**
```
(Empty response body)
```

**Use Case:** Permanently remove a user from the system.

---

### 11. Get User Statistics

**Request:**
```bash
curl http://localhost:8080/api/users/stats
```

**Response:**
```json
{
  "totalUsers": 3,
  "activeUsers": 2,
  "inactiveUsers": 1
}
```

**Use Case:** Get overview of user counts in the system.

---

## 🔄 Complete Workflow Example

Here's a complete workflow demonstrating the typical user lifecycle:

```bash
# 1. Check application health
curl http://localhost:8080/api/hello/health

# 2. View existing users
curl http://localhost:8080/api/users

# 3. Create a new user
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "firstName": "Test",
    "lastName": "User"
  }'

# 4. Verify creation by getting all users
curl http://localhost:8080/api/users

# 5. Get the specific user (assuming ID 4)
curl http://localhost:8080/api/users/4

# 6. Update the user
curl -X PUT http://localhost:8080/api/users/4 \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser_updated",
    "email": "test.updated@example.com",
    "firstName": "Test",
    "lastName": "User Updated"
  }'

# 7. Search for the user
curl "http://localhost:8080/api/users/search?name=Test"

# 8. Check statistics
curl http://localhost:8080/api/users/stats

# 9. Deactivate the user
curl -X PATCH http://localhost:8080/api/users/4/deactivate

# 10. Verify user is inactive
curl http://localhost:8080/api/users/active

# 11. Delete the user permanently
curl -X DELETE http://localhost:8080/api/users/4

# 12. Verify deletion
curl http://localhost:8080/api/users
```

---

## 🧪 Testing Different Java Versions

To verify multi-version compatibility:

```bash
# Build with Java 8
mvn clean install -Pjava8
mvn spring-boot:run -Pjava8

# Build with Java 11
mvn clean install -Pjava11
mvn spring-boot:run -Pjava11

# Build with Java 17
mvn clean install -Pjava17
mvn spring-boot:run -Pjava17
```

Check the Java version in the health endpoint:
```bash
curl http://localhost:8080/api/hello/health | grep javaVersion
```

---

## 📱 Using with Postman

1. **Import Collection:** Copy the Postman collection from README.md
2. **Create Environment:** Set `baseUrl` = `http://localhost:8080`
3. **Run Tests:** Execute requests in order from the collection

---

## 🐛 Troubleshooting Examples

### Application Not Starting

```bash
# Check if port 8080 is in use
netstat -ano | findstr :8080  # Windows
lsof -i :8080                 # Linux/Mac

# Run on different port
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

### Connection Refused

```bash
# Verify application is running
curl http://localhost:8080/api/actuator/health

# Check application logs
tail -f logs/application.log
```

### Validation Errors

All fields must meet these requirements:
- **username:** 3-50 characters, not blank
- **email:** Valid email format, not blank
- **firstName:** 2-50 characters, not blank
- **lastName:** 2-50 characters, not blank

---

## 📊 Performance Testing Example

Using Apache Bench (ab):

```bash
# Install Apache Bench (if needed)
# Windows: Download from Apache website
# Linux: sudo apt-get install apache2-utils
# Mac: Comes pre-installed

# Test GET endpoint
ab -n 1000 -c 10 http://localhost:8080/api/users

# Results will show:
# - Requests per second
# - Time per request
# - Transfer rate
```

---

**Last Updated:** December 11, 2025
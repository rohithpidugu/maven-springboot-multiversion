# Maven Spring Boot Multi-Version Compatibility Project

![Build Status](https://github.com/rohithpidugu/maven-springboot-multiversion/workflows/Maven%20CI%2FCD%20Pipeline/badge.svg)
![Java](https://img.shields.io/badge/Java-8%20%7C%2011%20%7C%2017-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.18-brightgreen)
![Maven](https://img.shields.io/badge/Maven-3.9+-red)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

## 📚 Project Overview

This project demonstrates building a Spring Boot REST API using Maven with support for multiple Java versions (8, 11, 17). The goal is to understand Maven's build lifecycle, dependency management, and CI/CD integration.

## 🎯 Learning Objectives

- Master Maven project structure and POM configuration
- Implement Java version compatibility using Maven profiles
- Build a RESTful API with Spring Boot
- Understand Maven build lifecycle phases
- Integrate CI/CD pipeline with GitHub Actions
- Apply best practices for dependency management

## 🛠️ Technologies Used

- **Java**: 8, 11, 17
- **Build Tool**: Maven 3.9.x
- **Framework**: Spring Boot
- **CI/CD**: GitHub Actions
- **Version Control**: Git

## 📋 Project Structure

```
maven-springboot-multiversion/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
│       └── java/
├── pom.xml
├── README.md
└── .gitignore
```

## 🚀 Getting Started

### Prerequisites

- JDK 8, 11, or 17
- Maven 3.6+
- Git

### Installation Steps

```bash
# Clone the repository
git clone https://github.com/yourusername/maven-springboot-multiversion.git
cd maven-springboot-multiversion

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```
## 📡 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Endpoints Overview

| Method | Endpoint | Description | Status Codes |
|--------|----------|-------------|--------------|
| GET | `/hello` | Welcome message | 200 |
| GET | `/hello/health` | Application health check | 200 |
| GET | `/users` | Get all users | 200 |
| GET | `/users/{id}` | Get user by ID | 200, 404 |
| GET | `/users/active` | Get active users only | 200 |
| GET | `/users/search?name={name}` | Search users by name | 200 |
| GET | `/users/stats` | Get user statistics | 200 |
| POST | `/users` | Create new user | 201, 400 |
| PUT | `/users/{id}` | Update user | 200, 400, 404 |
| PATCH | `/users/{id}/deactivate` | Deactivate user | 200, 404 |
| DELETE | `/users/{id}` | Delete user | 204, 404 |

### Detailed Endpoint Documentation

#### 1. Welcome Endpoint
```http
GET /api/hello
```

**Response:**
```json
{
  "message": "Hello from Maven Spring Boot Multi-Version API!",
  "status": "success"
}
```

---

#### 2. Health Check
```http
GET /api/hello/health
```

**Response:**
```json
{
  "status": "UP",
  "application": "maven-springboot-multiversion",
  "javaVersion": "11.0.29",
  "timestamp": 1702156800000
}
```

---

#### 3. Get All Users
```http
GET /api/users
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

---

#### 4. Get User by ID
```http
GET /api/users/{id}
```

**Path Parameters:**
- `id` (Long): User ID

**Success Response (200):**
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

**Error Response (404):**
```json
{
  "timestamp": "2024-12-10T12:00:00",
  "status": 404,
  "error": "Not Found"
}
```

---

#### 5. Create User
```http
POST /api/users
Content-Type: application/json
```

**Request Body:**
```json
{
  "username": "newuser",
  "email": "newuser@example.com",
  "firstName": "New",
  "lastName": "User"
}
```

**Validation Rules:**
- `username`: Required, 3-50 characters
- `email`: Required, valid email format
- `firstName`: Required, 2-50 characters
- `lastName`: Required, 2-50 characters

**Success Response (201):**
```json
{
  "id": 4,
  "username": "newuser",
  "email": "newuser@example.com",
  "firstName": "New",
  "lastName": "User",
  "active": true
}
```

**Validation Error Response (400):**
```json
{
  "timestamp": "2024-12-10T12:00:00",
  "status": 400,
  "error": "Validation Failed",
  "message": "Invalid input data",
  "validationErrors": {
    "email": "Email must be valid",
    "username": "Username must be between 3 and 50 characters"
  },
  "path": "/api/users"
}
```

---

#### 6. Update User
```http
PUT /api/users/{id}
Content-Type: application/json
```

**Path Parameters:**
- `id` (Long): User ID to update

**Request Body:**
```json
{
  "username": "updateuser",
  "email": "updated@example.com",
  "firstName": "Updated",
  "lastName": "User"
}
```

**Success Response (200):**
```json
{
  "id": 1,
  "username": "updateuser",
  "email": "updated@example.com",
  "firstName": "Updated",
  "lastName": "User",
  "active": true
}
```

---

#### 7. Delete User
```http
DELETE /api/users/{id}
```

**Path Parameters:**
- `id` (Long): User ID to delete

**Success Response (204):**
No content

**Error Response (404):**
User not found

---

#### 8. Search Users
```http
GET /api/users/search?name={searchTerm}
```

**Query Parameters:**
- `name` (String): Search term for first or last name

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

---

#### 9. Get User Statistics
```http
GET /api/users/stats
```

**Response:**
```json
{
  "totalUsers": 3,
  "activeUsers": 3,
  "inactiveUsers": 0
}
```

---

### cURL Examples

```bash
# Get all users
curl http://localhost:8080/api/users

# Get user by ID
curl http://localhost:8080/api/users/1

# Create new user
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "alice",
    "email": "alice@example.com",
    "firstName": "Alice",
    "lastName": "Wonder"
  }'

# Update user
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "username": "johndoe_updated",
    "email": "john.updated@example.com",
    "firstName": "John",
    "lastName": "Doe"
  }'

# Delete user
curl -X DELETE http://localhost:8080/api/users/1

# Search users
curl "http://localhost:8080/api/users/search?name=John"

# Get statistics
curl http://localhost:8080/api/users/stats
```

---

### Postman Collection

Import this into Postman for easy API testing:

```json
{
  "info": {
    "name": "Maven Spring Boot Multi-Version API",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Get All Users",
      "request": {
        "method": "GET",
        "url": "http://localhost:8080/api/users"
      }
    },
    {
      "name": "Create User",
      "request": {
        "method": "POST",
        "url": "http://localhost:8080/api/users",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\n  \"username\": \"testuser\",\n  \"email\": \"test@example.com\",\n  \"firstName\": \"Test\",\n  \"lastName\": \"User\"\n}"
        }
      }
    }
  ]
}
```
## 📖 Maven Build Lifecycle

This project demonstrates understanding of Maven's build lifecycle:

1. **validate** - Validate project structure
2. **compile** - Compile source code
3. **test** - Run unit tests
4. **package** - Package compiled code (JAR/WAR)
5. **verify** - Run integration tests
6. **install** - Install package to local repository
7. **deploy** - Deploy to remote repository

## 🔄 Java Version Profiles

The project uses Maven profiles to support multiple Java versions:

```bash
# Build with Java 8
mvn clean install -Pjava8

# Build with Java 11
mvn clean install -Pjava11

# Build with Java 17
mvn clean install -Pjava17
```

## 🧪 Testing

```bash
# Run all tests
mvn test

# Run tests with coverage
mvn test jacoco:report
```

## 📦 Packaging

```bash
# Create JAR file
mvn clean package

# Skip tests during packaging
mvn clean package -DskipTests
```

## 🔍 Code Quality

```bash
# Run static analysis
mvn checkstyle:check

# Generate site documentation
mvn site
```

## 🚀 Deployment

Automated deployment is configured via GitHub Actions. See `.github/workflows/` for CI/CD pipeline configuration.

## 📝 Development Log

### Day 1: Project Initialization
- Set up Maven project structure
- Configured POM for multi-version support
- Created basic project documentation

### Day 2: API Development
- Implemented REST endpoints
- Added dependency management
- Configured Maven plugins

### Day 3: CI/CD Integration
- Set up GitHub Actions workflow
- Added automated testing
- Configured deployment pipeline

## 🤝 Contributing

This is a learning project. Feedback and suggestions are welcome!

## 📄 License

This project is created for educational purposes.

## 👨‍💻 Author

**Your Name**
- GitHub: [@rohithpidugu](https://github.com/rohithpidugu)
- LinkedIn: [Rohith Raj Pidugu](https://linkedin.com/in/rohithrajpidugu)

## 🙏 Acknowledgments

- Maven Documentation
- Spring Boot Guides
- Baeldung Java Tutorials

---

**Note**: This project is part of my journey learning Maven, Spring Boot, and DevOps practices.
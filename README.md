# Maven Spring Boot Multi-Version Compatibility Project

![Build Status](https://github.com/rohithpidugu/maven-springboot-multiversion/workflows/Maven%20CI%2FCD%20Pipeline/badge.svg)
![Java](https://img.shields.io/badge/Java-8%20%7C%2011%20%7C%2017-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.18-brightgreen)
![Maven](https://img.shields.io/badge/Maven-3.9+-red)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

## 📚 Project Overview

This project demonstrates building a Spring Boot REST API using Maven with support for multiple Java versions (8, 11, 17). The goal is to understand Maven's build lifecycle, dependency management, and CI/CD integration while showcasing professional software development practices.

## 🎯 Learning Objectives

- Master Maven project structure and POM configuration
- Implement Java version compatibility using Maven profiles
- Build a RESTful API with Spring Boot
- Understand Maven build lifecycle phases
- Integrate CI/CD pipeline with GitHub Actions
- Apply best practices for dependency management
- Achieve high test coverage with comprehensive testing

## 🛠️ Technologies Used

- **Java**: 8, 11, 17
- **Build Tool**: Maven 3.9+
- **Framework**: Spring Boot 2.7.18
- **Testing**: JUnit 5, MockMvc
- **Code Quality**: Checkstyle, JaCoCo
- **CI/CD**: GitHub Actions
- **Version Control**: Git

## 🎯 Project Features

### Core Features
- ✅ **Multi-Version Java Support** - Builds with Java 8, 11, and 17
- ✅ **RESTful API** - Complete CRUD operations
- ✅ **Input Validation** - Bean Validation (JSR-380)
- ✅ **Exception Handling** - Global error handling
- ✅ **Comprehensive Testing** - 80%+ code coverage
- ✅ **Code Quality** - Checkstyle integration
- ✅ **CI/CD Pipeline** - GitHub Actions automation
- ✅ **Monitoring** - Spring Boot Actuator endpoints
- ✅ **Logging** - Logback with file rotation
- ✅ **Documentation** - Complete API documentation

### Technical Highlights
- Maven profiles for version management
- In-memory data storage for demonstration
- Spring Boot DevTools for hot reload
- JaCoCo for test coverage reporting
- Docker support with multi-stage builds
- Cloud deployment ready (AWS, Azure, Heroku, GCP)

## 📋 Project Structure

```
maven-springboot-multiversion/
├── .github/
│   └── workflows/
│       └── maven-build.yml          # CI/CD pipeline
├── src/
│   ├── main/
│   │   ├── java/com/example/api/
│   │   │   ├── Application.java     # Main entry point
│   │   │   ├── controller/
│   │   │   │   ├── HelloController.java
│   │   │   │   └── UserController.java
│   │   │   ├── service/
│   │   │   │   └── UserService.java
│   │   │   ├── model/
│   │   │   │   └── User.java
│   │   │   └── exception/
│   │   │       └── GlobalExceptionHandler.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── banner.txt
│   │       └── logback-spring.xml
│   └── test/
│       └── java/com/example/api/
│           ├── controller/
│           │   └── UserControllerTest.java
│           └── service/
│               └── UserServiceTest.java
├── logs/                            # Application logs
├── target/                          # Build output
├── pom.xml                          # Maven configuration
├── README.md                        # This file
├── LEARNING_NOTES.md               # Maven learning documentation
├── API_EXAMPLES.md                 # API usage examples
├── CONTRIBUTING.md                 # Contribution guidelines
├── DEPLOYMENT.md                   # Deployment guide
├── TROUBLESHOOTING.md              # Common issues and solutions
├── LICENSE                         # MIT License
└── .gitignore                      # Git ignore rules
```

## 🚀 Getting Started

### Prerequisites

- JDK 8, 11, or 17
- Maven 3.6+
- Git

### Installation Steps

```bash
# Clone the repository
git clone https://github.com/rohithpidugu/maven-springboot-multiversion.git
cd maven-springboot-multiversion

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Quick Test

```bash
# Test the API
curl http://localhost:8080/api/hello
curl http://localhost:8080/api/users
```

## ⚡ Quick Reference

### API Endpoints Summary

| Category | Method | Endpoint | Description |
|----------|--------|----------|-------------|
| **Health** | GET | `/api/hello` | Welcome message |
| **Health** | GET | `/api/hello/health` | Health check with Java version |
| **Users** | GET | `/api/users` | Get all users |
| **Users** | GET | `/api/users/{id}` | Get user by ID |
| **Users** | GET | `/api/users/active` | Get active users only |
| **Users** | GET | `/api/users/search?name={name}` | Search users by name |
| **Users** | GET | `/api/users/stats` | Get user statistics |
| **Users** | POST | `/api/users` | Create new user |
| **Users** | PUT | `/api/users/{id}` | Update user |
| **Users** | PATCH | `/api/users/{id}/deactivate` | Deactivate user |
| **Users** | DELETE | `/api/users/{id}` | Delete user |
| **Actuator** | GET | `/api/actuator/health` | Spring Boot health |
| **Actuator** | GET | `/api/actuator/info` | Application info |
| **Actuator** | GET | `/api/actuator/metrics` | Application metrics |

### Maven Profile Commands

```bash
# Build with Java 8
mvn clean install -Pjava8

# Build with Java 11 (default)
mvn clean install -Pjava11

# Build with Java 17
mvn clean install -Pjava17

# Run tests
mvn test

# Generate coverage report
mvn jacoco:report

# Run code quality check
mvn checkstyle:check

# Package as JAR
mvn clean package

# Run the application
mvn spring-boot:run
```

### Project Statistics

- **Total Endpoints:** 14
- **Test Coverage:** 80%+
- **Java Versions Supported:** 3 (8, 11, 17)
- **CI/CD:** GitHub Actions
- **Code Quality Tools:** Checkstyle + JaCoCo

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

Each profile configures the appropriate compiler source and target versions.

## 🧪 Testing

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=UserServiceTest

# Run tests with coverage
mvn test jacoco:report

# View coverage report
open target/site/jacoco/index.html
```

### Testing Strategy
- **Controller Layer:** MockMvc tests with mocked services
- **Service Layer:** Business logic tests with real data
- **Coverage Target:** 80%+ line coverage

## 📦 Packaging

```bash
# Create JAR file
mvn clean package

# Skip tests during packaging
mvn clean package -DskipTests

# Run the JAR
java -jar target/maven-springboot-multiversion.jar
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

For detailed deployment instructions, see [DEPLOYMENT.md](DEPLOYMENT.md)

## 📚 Documentation

- **[LEARNING_NOTES.md](LEARNING_NOTES.md)** - Comprehensive Maven learning documentation
- **[API_EXAMPLES.md](API_EXAMPLES.md)** - Detailed API usage examples with cURL commands
- **[CONTRIBUTING.md](CONTRIBUTING.md)** - Guidelines for contributing to the project
- **[DEPLOYMENT.md](DEPLOYMENT.md)** - Deployment guide for various platforms
- **[TROUBLESHOOTING.md](TROUBLESHOOTING.md)** - Common issues and solutions

## 🧪 API Examples

### Create a User

```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "johndoe",
    "email": "john@example.com",
    "firstName": "John",
    "lastName": "Doe"
  }'
```

### Get All Users

```bash
curl http://localhost:8080/api/users
```

### Search Users

```bash
curl "http://localhost:8080/api/users/search?name=John"
```

For more examples, see [API_EXAMPLES.md](API_EXAMPLES.md)

## 🔐 Security Features

- Input validation on all API endpoints
- Global exception handling
- Secure error messages (no stack traces to clients)
- Actuator endpoints configured for security
- Production-ready security practices

## 💼 Skills Demonstrated

This project showcases proficiency in:

### Backend Development
- Java programming (8, 11, 17)
- Spring Boot framework
- RESTful API design
- Maven build tool

### Testing
- Unit testing with JUnit 5
- MockMvc for API testing
- Test coverage analysis
- Test-driven development

### DevOps
- CI/CD with GitHub Actions
- Docker containerization
- Cloud deployment strategies
- Version management

### Software Engineering
- Design patterns
- SOLID principles
- Clean code practices
- Comprehensive documentation

## 🌟 What Makes This Project Special

### For Learning
- **Comprehensive Documentation** - Every aspect is documented
- **Best Practices** - Industry-standard patterns and conventions
- **Real-World Application** - Practical REST API implementation
- **Multi-Version Support** - Demonstrates compatibility management

### For Job Applications
- **Professional Structure** - Enterprise-level organization
- **Clean Commit History** - Shows systematic development
- **Testing Coverage** - Demonstrates quality focus
- **CI/CD Integration** - Modern DevOps practices
- **Complete Documentation** - Shows communication skills

## 📈 Future Enhancements

Potential improvements for this project:

- [ ] Add database integration (PostgreSQL/MySQL)
- [ ] Implement Spring Security with JWT
- [ ] Add Swagger/OpenAPI documentation
- [ ] Implement pagination and sorting
- [ ] Add caching with Redis
- [ ] Implement rate limiting
- [ ] Add GraphQL endpoint
- [ ] Containerize with Kubernetes
- [ ] Add monitoring with Prometheus/Grafana
- [ ] Implement event-driven architecture

## 🤝 Contributing

Contributions are welcome! Please read [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

## 📝 Development Log

### Day 1: Project Initialization
- Set up Maven project structure
- Configured POM for multi-version support
- Created basic project documentation
- Implemented Spring Boot application
- Added REST endpoints

### Day 2: API Development
- Implemented User management CRUD operations
- Added dependency management
- Configured Maven plugins
- Added comprehensive unit tests
- Implemented validation and exception handling

### Day 3: CI/CD Integration
- Set up GitHub Actions workflow
- Added automated testing
- Configured deployment pipeline
- Added code quality tools (Checkstyle, JaCoCo)
- Completed comprehensive documentation

## 🆘 Troubleshooting

Having issues? Check [TROUBLESHOOTING.md](TROUBLESHOOTING.md) for solutions to common problems.

## 📞 Contact & Support

### Project Links
- **GitHub Repository:** https://github.com/rohithpidugu/maven-springboot-multiversion
- **Issues:** https://github.com/rohithpidugu/maven-springboot-multiversion/issues
- **CI/CD:** https://github.com/rohithpidugu/maven-springboot-multiversion/actions

### Getting Help
- 📖 Check [TROUBLESHOOTING.md](TROUBLESHOOTING.md) for common issues
- 📘 Review [LEARNING_NOTES.md](LEARNING_NOTES.md) for Maven concepts
- 🚀 See [DEPLOYMENT.md](DEPLOYMENT.md) for deployment help
- 💬 Create an issue for bugs or feature requests

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Rohith Pidugu**
- GitHub: [@rohithpidugu](https://github.com/rohithpidugu)

## 🙏 Acknowledgments

This project was built as a comprehensive learning exercise to demonstrate:
- Maven build tool proficiency
- Spring Boot REST API development
- Multi-version Java compatibility
- Professional software development practices
- DevOps and CI/CD integration

Special thanks to:
- Spring Boot team for the excellent framework
- Maven community for comprehensive documentation
- GitHub Actions for CI/CD platform
- Open source community for inspiration

---

⭐ If this project helped you learn Maven and Spring Boot, please consider giving it a star!

**Project Status:** ✅ Complete and Production-Ready

**Last Updated:** December 10, 2025 | **Version:** 1.0.0-SNAPSHOT
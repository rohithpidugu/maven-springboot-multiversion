# Maven Spring Boot Multi-Version Compatibility Project

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
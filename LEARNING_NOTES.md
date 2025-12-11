# Maven Learning Notes

## 📘 Understanding Maven Basics

### What is Maven?
Maven is a powerful build automation and project management tool primarily used for Java projects. It simplifies:
- Dependency management  
- Project building  
- Testing automation  
- Deployment processes  

### Key Maven Concepts

#### 1. Project Object Model (POM)
The `pom.xml` file is the core of a Maven project. It contains:
- Project metadata (groupId, artifactId, version)
- Dependencies
- Build configuration
- Plugins

**Example:**
```xml
<groupId>com.example</groupId>
<artifactId>maven-springboot-multiversion</artifactId>
<version>1.0.0-SNAPSHOT</version>
```

#### 2. Maven Coordinates (GAV)
Every Maven artifact is uniquely identified by:
- **GroupId** – Organization/company identifier  
- **ArtifactId** – Project/module name  
- **Version** – Release identifier  

---

## 🔄 Maven Build Lifecycle

Maven provides three major lifecycles: **default**, **clean**, and **site**.  
Below is a summary of the most important phases:

| Phase | Description |
|-------|-------------|
| `validate` | Validate project structure |
| `compile` | Compile source code |
| `test` | Run unit tests |
| `package` | Create JAR/WAR |
| `verify` | Run integration tests |
| `install` | Install artifact to local repo |
| `deploy` | Deploy to remote repo |

Running a phase also runs all previous phases automatically.

---

## 📦 Dependency Management

### Dependency Scopes

| Scope | Description | Available In |
|-------|-------------|--------------|
| `compile` | Default, available everywhere | All classpaths |
| `provided` | Provided by container/JDK | Compile & test |
| `runtime` | Needed only at runtime | Runtime & test |
| `test` | Only for tests | Test classpath |
| `system` | Explicit path required | Similar to provided |

Example:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

---

## 🔌 Maven Plugins Overview

### Spring Boot Plugin
Creates executable JARs and runs apps:
```xml
<artifactId>spring-boot-maven-plugin</artifactId>
```

### Maven Compiler Plugin
Sets Java versions:
```xml
<source>11</source>
<target>11</target>
```

### Maven Surefire Plugin
Runs unit tests.

---

## 🧰 Common Maven Commands

```bash
mvn clean compile
mvn test
mvn clean package
mvn clean install
mvn spring-boot:run
mvn dependency:tree
```

---

# 📝 **Learning Phase 1: Project Setup & Core Concepts**

### Key Achievements
- Set up Maven project structure  
- Configured Spring Boot parent POM  
- Added core dependencies  
- Implemented initial REST endpoints  
- Configured build plugins  
- Successfully built and ran application  

### Maven Concepts Learned
- POM structure & dependency management  
- Build lifecycle fundamentals  
- Plugin configuration  
- Dependency scopes  
- Effective use of Maven commands  

---

# 📝 **Learning Phase 2: Multi-Version Support, API Development & Testing**

### Key Achievements
- Implemented Maven profiles for Java 8, 11, and 17  
- Created User model with validation  
- Implemented UserService with business logic  
- Developed full CRUD REST API  
- Added input validation (JSR-380)  
- Wrote unit tests using JUnit 5 + MockMvc  
- Implemented global exception handling  
- Configured structured logging with Logback  
- Added Checkstyle for code quality enforcement  

### Technical Concepts Learned
- Maven profiles and conditional builds  
- REST API design patterns  
- Bean Validation (JSR-380)  
- Testing REST endpoints with MockMvc  
- Exception handling with `@RestControllerAdvice`  
- Logging best practices (Logback)  
- Static code analysis with Checkstyle  

---

# 📝 **Learning Phase 3: CI/CD, Coverage, Documentation & Production Readiness**

### Key Achievements
- Set up GitHub Actions CI/CD pipeline  
- Added JaCoCo for test coverage reporting  
- Finalized complete documentation (README, API examples, troubleshooting, deployment)  
- Learned how to package and run applications in different environments  
- Ensured multi-version Java compatibility across profiles  

### Tools & Concepts Learned
- CI/CD automation  
- Coverage reporting  
- Release packaging  
- Effective documentation structure  
- Production-ready logging & validation patterns  

---

# 📅 Last Updated  
**December 11, 2025**
# Maven Learning Notes

## ��� Understanding Maven Basics

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
- **G**roupId: Organization/company identifier (e.g., `com.example`)
- **A**rtifactId: Project name (e.g., `maven-springboot-multiversion`)
- **V**ersion: Project version (e.g., `1.0.0-SNAPSHOT`)

---

## ��� Maven Build Lifecycle

Maven has three built-in build lifecycles:

### 1. Default Lifecycle (Main Build)
The most commonly used lifecycle with these phases:

| Phase | Description | Example Command |
|-------|-------------|-----------------|
| `validate` | Validates project structure | `mvn validate` |
| `compile` | Compiles source code | `mvn compile` |
| `test` | Runs unit tests | `mvn test` |
| `package` | Packages compiled code (JAR/WAR) | `mvn package` |
| `verify` | Runs integration tests | `mvn verify` |
| `install` | Installs to local repository (~/.m2) | `mvn install` |
| `deploy` | Deploys to remote repository | `mvn deploy` |

**Important:** When you run a phase, all preceding phases execute automatically.

Example: `mvn package` runs: validate → compile → test → package

### 2. Clean Lifecycle
Handles project cleaning:
- `pre-clean`: Pre-cleanup tasks
- `clean`: Removes `target/` directory
- `post-clean`: Post-cleanup tasks

**Usage:** `mvn clean` or `mvn clean install`

### 3. Site Lifecycle
Generates project documentation:
- `site`: Generates project site
- `site-deploy`: Deploys site to server

---

## ��� Dependency Management

### Dependency Scopes

| Scope | Description | Available In |
|-------|-------------|--------------|
| `compile` | Default scope, available everywhere | All classpaths |
| `provided` | Provided by JDK or container | Compile & test only |
| `runtime` | Not needed for compilation | Runtime & test |
| `test` | Only for testing | Test classpath |
| `system` | Must provide explicit path | Similar to provided |

**Example:**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <!-- No version needed - managed by parent POM -->
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

### Dependency Inheritance
Our project uses Spring Boot parent POM:
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.7.18</version>
</parent>
```

**Benefits:**
- Pre-configured dependency versions
- Common build configuration
- Plugin management

---

## ��� Maven Plugins

Plugins extend Maven's functionality. We use:

### 1. Spring Boot Maven Plugin
```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
</plugin>
```
**Purpose:** Creates executable JAR with embedded server

**Usage:** `mvn spring-boot:run`

### 2. Maven Compiler Plugin
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
        <source>11</source>
        <target>11</target>
    </configuration>
</plugin>
```
**Purpose:** Compiles Java source code with specific version

### 3. Maven Surefire Plugin
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
</plugin>
```
**Purpose:** Executes unit tests during build

---

## ��� Common Maven Commands

### Basic Commands
```bash
# Clean and compile
mvn clean compile

# Run tests
mvn test

# Package as JAR
mvn clean package

# Install to local repository
mvn clean install

# Skip tests during build
mvn clean install -DskipTests

# Run Spring Boot application
mvn spring-boot:run

# Show dependency tree
mvn dependency:tree

# Check for dependency updates
mvn versions:display-dependency-updates
```

### Useful Flags
```bash
-X              # Debug mode
-e              # Show error details
-DskipTests     # Skip test execution
-U              # Force update snapshots
-o              # Offline mode
```

---

## ���️ Project Structure

Maven follows "Convention over Configuration":

```
maven-springboot-multiversion/
├── src/
│   ├── main/
│   │   ├── java/              # Source code
│   │   │   └── com/example/api/
│   │   │       ├── Application.java
│   │   │       └── controller/
│   │   │           └── HelloController.java
│   │   └── resources/         # Configuration files
│   │       └── application.properties
│   └── test/
│       └── java/              # Test code
├── target/                    # Build output (generated)
├── pom.xml                    # Project configuration
└── README.md
```

---

## ��� Multi-Version Java Support

### Strategy for Supporting Multiple Java Versions

We'll use **Maven Profiles** to support Java 8, 11, and 17:

```xml
<profiles>
    <profile>
        <id>java8</id>
        <properties>
            <java.version>8</java.version>
            <maven.compiler.source>8</maven.compiler.source>
            <maven.compiler.target>8</maven.compiler.target>
        </properties>
    </profile>
    
    <profile>
        <id>java11</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <java.version>11</java.version>
            <maven.compiler.source>11</maven.compiler.source>
            <maven.compiler.target>11</maven.compiler.target>
        </properties>
    </profile>
</profiles>
```

**Usage:**
```bash
# Build with Java 8
mvn clean install -Pjava8

# Build with Java 11 (default)
mvn clean install -Pjava11

# Build with Java 17
mvn clean install -Pjava17
```

---

## ��� Key Learnings - Day 1

### Accomplished Today:
1. ✅ Set up Maven project structure
2. ✅ Configured POM with Spring Boot parent
3. ✅ Added dependencies with proper scopes
4. ✅ Created Spring Boot application
5. ✅ Implemented REST endpoints
6. ✅ Configured build plugins
7. ✅ Successfully ran and tested application

### Maven Skills Gained:
- Understanding POM structure
- Managing dependencies
- Using Maven lifecycle phases
- Configuring plugins
- Running Maven commands

### Next Steps (Day 2):
- Add Maven profiles for multi-version support
- Implement additional REST endpoints
- Add unit tests
- Configure code quality plugins
- Add more Spring Boot features

---

## ��� Resources

### Official Documentation
- [Maven Official Guide](https://maven.apache.org/guides/)
- [Maven Lifecycle Reference](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)
- [Spring Boot Maven Plugin](https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/)

### Useful Commands Reference
- [Maven Commands Cheat Sheet](https://maven.apache.org/guides/MavenQuickReferenceCard.pdf)
- [Maven POM Reference](https://maven.apache.org/pom.html)

---

**Last Updated:** Day 1 - December 7, 2025

---

## 📝 Key Learnings - Day 2

### Accomplished Today:
1. ✅ Implemented Maven profiles for Java 8, 11, 17 compatibility
2. ✅ Created User model with validation annotations
3. ✅ Built UserService with comprehensive business logic
4. ✅ Developed UserController with full CRUD REST API
5. ✅ Added Bean Validation for input validation
6. ✅ Wrote unit tests for Controller and Service layers
7. ✅ Implemented global exception handling
8. ✅ Configured custom logging with Logback
9. ✅ Added Checkstyle for code quality checks

### Maven Profiles in Action

**What are Maven Profiles?**
Maven profiles allow you to customize builds for different environments or Java versions. They enable conditional configuration based on active profiles.

**Our Profile Configuration:**
```xml
<profiles>
    <profile>
        <id>java8</id>
        <properties>
            <java.version>1.8</java.version>
        </properties>
    </profile>
    <profile>
        <id>java11</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <java.version>11</java.version>
        </properties>
    </profile>
</profiles>
```

**Activating Profiles:**
```bash
mvn clean install -Pjava8
mvn clean install -Pjava11
mvn clean install -Pjava17
```

**Key Concepts:**
- Profiles modify build behavior without changing code
- `<activation>` can make profiles active by default
- Properties defined in profiles override parent properties
- Essential for multi-version compatibility

---

### Testing with Maven

**Test Lifecycle:**
- Tests run automatically during `mvn test` phase
- Tests must pass for `mvn package` to succeed
- Use `-DskipTests` to skip tests (not recommended)

**Testing Annotations:**
- `@WebMvcTest` - Tests Spring MVC controllers
- `@MockBean` - Mocks Spring beans in tests
- `@BeforeEach` - Setup method before each test
- `@Test` - Marks a test method

**MockMvc for API Testing:**
```java
mockMvc.perform(get("/users"))
    .andExpect(status().isOk())
    .andExpect(jsonPath("$", hasSize(3)));
```

**Benefits:**
- Tests HTTP endpoints without starting full server
- Validates response status codes
- Verifies JSON structure and content
- Fast execution compared to integration tests

---

### Bean Validation (JSR-380)

**Common Validation Annotations:**
- `@NotNull` - Field cannot be null
- `@NotBlank` - String cannot be null or empty
- `@Size(min, max)` - String/Collection size constraints
- `@Email` - Validates email format
- `@Min/@Max` - Numeric range validation
- `@Pattern` - Regex validation

**Enabling Validation:**
1. Add `spring-boot-starter-validation` dependency
2. Add annotations to model fields
3. Use `@Valid` in controller methods

**Example:**
```java
public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
    // Validation happens automatically before method execution
    // If invalid, MethodArgumentNotValidException is thrown
}
```

---

### Exception Handling with @RestControllerAdvice

**Global Exception Handler Benefits:**
- Centralized error handling across all controllers
- Consistent error response format
- Cleaner controller code (no try-catch blocks)
- Better API consumer experience

**Common Exception Handlers:**
- `MethodArgumentNotValidException` - Validation errors
- `IllegalArgumentException` - Invalid input
- `Exception` - Catch-all for unexpected errors

**Error Response Structure:**
```json
{
  "timestamp": "2024-12-08T12:00:00",
  "status": 400,
  "error": "Validation Failed",
  "message": "Invalid input data",
  "validationErrors": {
    "email": "Email must be valid",
    "username": "Username is required"
  },
  "path": "/api/users"
}
```

---

### Logging Best Practices

**Logback Configuration:**
- Console appender for development
- File appender for production logs
- Separate error log file
- Rolling file policy (daily, 30-day retention)

**Log Levels:**
- `ERROR` - Application errors
- `WARN` - Warning messages
- `INFO` - Important events (default for production)
- `DEBUG` - Detailed information (development)
- `TRACE` - Very detailed information

**Package-Level Logging:**
```xml
<logger name="com.example.api" level="DEBUG" />
<logger name="org.springframework" level="INFO" />
```

**Why Logging Matters:**
- Debugging production issues
- Monitoring application health
- Audit trail for operations
- Performance analysis

---

### Code Quality with Checkstyle

**What is Checkstyle?**
Static code analysis tool that enforces coding standards and best practices.

**Benefits:**
- Consistent code formatting
- Catches common mistakes
- Enforces team coding standards
- Improves code readability

**Running Checkstyle:**
```bash
mvn checkstyle:check
mvn checkstyle:checkstyle  # Generates HTML report
```

**Google Java Style Guide:**
- Indentation: 2 spaces
- Line length: 100 characters
- Naming conventions enforced
- Import order standardized

---

### RESTful API Design Principles Applied

**HTTP Methods:**
- `GET` - Retrieve resources (idempotent)
- `POST` - Create new resources
- `PUT` - Update entire resource (idempotent)
- `PATCH` - Partial update
- `DELETE` - Remove resource (idempotent)

**HTTP Status Codes Used:**
- `200 OK` - Successful GET, PUT, PATCH
- `201 Created` - Successful POST
- `204 No Content` - Successful DELETE
- `400 Bad Request` - Validation errors
- `404 Not Found` - Resource not found
- `500 Internal Server Error` - Server errors

**Resource Naming:**
- Plural nouns: `/users` not `/user`
- Hierarchical: `/users/{id}/orders`
- Query params for filtering: `/users/search?name=John`
- Consistent naming across endpoints

---

### In-Memory Data Storage Pattern

**Why In-Memory Storage?**
- Simple for learning and demonstrations
- No database setup required
- Fast read/write operations
- Easy to reset and test

**Implementation:**
```java
private final List<User> users = new ArrayList<>();
private final AtomicLong idCounter = new AtomicLong(1);
```

**Thread Safety:**
- `AtomicLong` ensures thread-safe ID generation
- `ArrayList` is NOT thread-safe (use `CopyOnWriteArrayList` for production)
- For real applications, use database with transactions

---

### Maven Commands Learned Today

```bash
# Run tests only
mvn test

# Run tests with specific test class
mvn test -Dtest=UserServiceTest

# Run with specific profile
mvn clean install -Pjava11

# Generate test coverage report
mvn test jacoco:report

# Run checkstyle analysis
mvn checkstyle:check

# Package without tests
mvn clean package -DskipTests

# Run application
mvn spring-boot:run

# Show effective POM (with profiles applied)
mvn help:effective-pom -Pjava8
```

---

### Next Steps (Day 3):
- Set up GitHub Actions CI/CD pipeline
- Add JaCoCo for test coverage reporting
- Create comprehensive README with API documentation
- Add Swagger/OpenAPI documentation
- Configure deployment settings
- Final project polish and documentation

---

**Last Updated:** Day 2 - December 8, 2025

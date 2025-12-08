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

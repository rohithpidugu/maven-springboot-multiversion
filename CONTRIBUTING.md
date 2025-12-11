# Contributing to Maven Spring Boot Multi-Version Project

Thank you for your interest in contributing to this project! This document provides guidelines and instructions for contributing.

## 📋 Table of Contents

- [Code of Conduct](#code-of-conduct)
- [Getting Started](#getting-started)
- [Development Setup](#development-setup)
- [How to Contribute](#how-to-contribute)
- [Coding Standards](#coding-standards)
- [Testing Guidelines](#testing-guidelines)
- [Commit Message Guidelines](#commit-message-guidelines)
- [Pull Request Process](#pull-request-process)

---

## 🤝 Code of Conduct

This project adheres to a code of conduct. By participating, you are expected to uphold this code:

- Be respectful and inclusive
- Welcome newcomers and help them learn
- Focus on what is best for the community
- Show empathy towards other community members

---

## 🚀 Getting Started

### Prerequisites

Before contributing, ensure you have:

- JDK 8, 11, or 17 installed
- Maven 3.6+ installed
- Git installed
- A GitHub account
- A code editor (VS Code, IntelliJ IDEA, Eclipse)

### Fork the Repository

1. Fork the repository on GitHub
2. Clone your fork locally:
   ```bash
   git clone https://github.com/YOUR_USERNAME/maven-springboot-multiversion.git
   cd maven-springboot-multiversion
   ```
3. Add upstream remote:
   ```bash
   git remote add upstream https://github.com/rohithpidugu/maven-springboot-multiversion.git
   ```

---

## 🛠️ Development Setup

### 1. Install Dependencies

```bash
# Build the project
mvn clean install

# Run tests
mvn test

# Run the application
mvn spring-boot:run
```

### 2. Verify Setup

```bash
# Test all Java profiles
mvn clean install -Pjava8
mvn clean install -Pjava11
mvn clean install -Pjava17

# Run checkstyle
mvn checkstyle:check

# Generate coverage report
mvn jacoco:report
```

### 3. Configure IDE

**VS Code:**
- Install "Extension Pack for Java"
- Install "Spring Boot Extension Pack"
- Configure Java versions in settings

**IntelliJ IDEA:**
- Import as Maven project
- Configure project SDK (JDK 11 recommended)
- Enable Maven auto-import

---

## 💡 How to Contribute

### Types of Contributions

We welcome various types of contributions:

1. **Bug Fixes** - Fix issues in existing code
2. **New Features** - Add new functionality
3. **Documentation** - Improve or add documentation
4. **Tests** - Add or improve test coverage
5. **Code Quality** - Refactoring and optimization
6. **CI/CD** - Improve build and deployment processes

### Finding Issues to Work On

- Check the [Issues](https://github.com/rohithpidugu/maven-springboot-multiversion/issues) tab
- Look for issues labeled `good first issue` for beginners
- Issues labeled `help wanted` need contributors
- Feel free to create new issues for bugs or feature requests

---

## 📝 Coding Standards

### Java Code Style

We follow the **Google Java Style Guide**:

- Indentation: 2 spaces (not tabs)
- Line length: 100 characters maximum
- Use meaningful variable names
- Add JavaDoc comments for public methods
- Follow naming conventions:
  - Classes: `PascalCase`
  - Methods/Variables: `camelCase`
  - Constants: `UPPER_SNAKE_CASE`

### Code Quality Checks

Before submitting:

```bash
# Run checkstyle
mvn checkstyle:check

# Ensure all tests pass
mvn test

# Check code coverage
mvn jacoco:report
```

### Example Good Code

```java
/**
 * Retrieves a user by their unique identifier.
 * 
 * @param id The user's unique identifier
 * @return Optional containing the user if found, empty otherwise
 */
public Optional<User> getUserById(Long id) {
    return users.stream()
            .filter(user -> user.getId().equals(id))
            .findFirst();
}
```

---

## 🧪 Testing Guidelines

### Writing Tests

- Every new feature must include tests
- Aim for at least 70% code coverage
- Write both positive and negative test cases
- Use descriptive test method names

### Test Naming Convention

```java
// Pattern: methodName_whenCondition_shouldExpectedBehavior

@Test
void getUserById_WhenUserExists_ShouldReturnUser() {
    // Test implementation
}

@Test
void createUser_WithInvalidEmail_ShouldThrowValidationException() {
    // Test implementation
}
```

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=UserServiceTest

# Run with coverage
mvn test jacoco:report

# View coverage report
open target/site/jacoco/index.html
```

---

## 📜 Commit Message Guidelines

We follow the **Conventional Commits** specification:

### Format

```
<type>(<scope>): <subject>

<body>

<footer>
```

### Types

- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `test`: Adding or updating tests
- `refactor`: Code refactoring
- `style`: Code style changes (formatting)
- `chore`: Maintenance tasks
- `ci`: CI/CD changes

### Examples

```bash
# Good commit messages
feat: Add user search functionality
fix: Resolve null pointer exception in UserService
docs: Update API documentation with new endpoints
test: Add unit tests for UserController
refactor: Simplify user validation logic
ci: Add Java 17 to CI/CD matrix

# Bad commit messages
update code
fix bug
changes
WIP
```

### Detailed Example

```
feat(user): Add user deactivation endpoint

- Implement PATCH /users/{id}/deactivate endpoint
- Add deactivate method to UserService
- Update UserController with new endpoint
- Add unit tests for deactivation logic
- Update API documentation

Closes #42
```

---

## 🔄 Pull Request Process

### 1. Create a Feature Branch

```bash
# Update your fork
git fetch upstream
git checkout master
git merge upstream/master

# Create feature branch
git checkout -b feature/your-feature-name
```

### 2. Make Your Changes

- Write clean, readable code
- Follow coding standards
- Add tests for new functionality
- Update documentation if needed

### 3. Test Your Changes

```bash
# Run all checks
mvn clean install
mvn test
mvn checkstyle:check

# Test all Java versions
mvn clean install -Pjava8
mvn clean install -Pjava11
mvn clean install -Pjava17
```

### 4. Commit Your Changes

```bash
git add .
git commit -m "feat: Add your feature description"
```

### 5. Push to Your Fork

```bash
git push origin feature/your-feature-name
```

### 6. Create Pull Request

1. Go to your fork on GitHub
2. Click "New Pull Request"
3. Select your feature branch
4. Fill in the PR template:
   - Description of changes
   - Related issue number
   - Testing performed
   - Screenshots (if applicable)

### 7. PR Review Process

- Maintainers will review your PR
- Address any requested changes
- CI/CD checks must pass
- At least one approval required
- Squash and merge after approval

---

## 📊 Code Review Checklist

Before requesting review, ensure:

- [ ] Code follows project style guide
- [ ] All tests pass locally
- [ ] New features include tests
- [ ] Documentation is updated
- [ ] Commit messages follow conventions
- [ ] No merge conflicts
- [ ] CI/CD pipeline passes
- [ ] Code coverage maintained or improved

---

## 🐛 Reporting Bugs

When reporting bugs, include:

1. **Description**: Clear description of the bug
2. **Steps to Reproduce**: Detailed steps
3. **Expected Behavior**: What should happen
4. **Actual Behavior**: What actually happens
5. **Environment**: Java version, OS, Maven version
6. **Logs**: Relevant error logs or stack traces

### Bug Report Template

```markdown
**Description:**
Brief description of the bug

**Steps to Reproduce:**
1. Step one
2. Step two
3. Step three

**Expected Behavior:**
What should happen

**Actual Behavior:**
What actually happens

**Environment:**
- Java Version: 11.0.29
- Maven Version: 3.9.11
- OS: Windows 11
- Spring Boot Version: 2.7.18

**Logs:**
```
[Paste relevant logs here]
```
```

---

## 💬 Getting Help

- **GitHub Issues**: For bugs and feature requests  
- **Discussions**: For questions and collaboration  
- **Email**: contact@rohithpidugu.com

---

## 🎯 Development Tips

### Useful Maven Commands

```bash
mvn clean install
mvn clean install -DskipTests
mvn spring-boot:run -Pjava11
mvn clean verify site
mvn versions:display-dependency-updates
```

### Debugging

```bash
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"
```

---

## 📚 Additional Resources

- [Maven Documentation](https://maven.apache.org/guides/)
- [Spring Boot Guides](https://spring.io/guides)
- [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
- [Conventional Commits](https://www.conventionalcommits.org/)

---

## 🙏 Thank You!

Thank you for contributing to this project! Every contribution, no matter how small, is valuable and appreciated.

---

**Last Updated:** December 11, 2025
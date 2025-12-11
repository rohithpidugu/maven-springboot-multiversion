# Troubleshooting Guide

This guide helps you resolve common issues when working with the Maven Spring Boot Multi-Version API project.

## 📋 Table of Contents

- [Build Issues](#build-issues)
- [Runtime Issues](#runtime-issues)
- [Testing Issues](#testing-issues)
- [Maven Issues](#maven-issues)
- [Java Version Issues](#java-version-issues)
- [CI/CD Issues](#cicd-issues)
- [Performance Issues](#performance-issues)

---

## 🔨 Build Issues

### Issue: Maven Build Fails

**Symptom:**
```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin
```

**Solutions:**

1. **Check Java version:**
```bash
java -version
mvn -version
```

2. **Clean Maven cache:**
```bash
mvn clean
rm -rf ~/.m2/repository
mvn clean install
```

3. **Update Maven wrapper:**
```bash
mvn -N io.takari:maven:wrapper
```

---

### Issue: Compilation Error - Invalid Target Release

**Symptom:**
```
[ERROR] Fatal error compiling: error: invalid target release: 17
```

**Cause:** You're trying to compile for Java 17 but your `JAVA_HOME` points to an earlier version.

**Solutions:**

**Option 1: Switch Java version**
```bash
# Switch to Java 17
export JAVA_HOME="/path/to/jdk-17"
java -version
```

**Option 2: Build with matching profile**
```bash
# Use Java 11 if that's what you have
mvn clean install -Pjava11
```

**Option 3: Update JAVA_HOME permanently**

Edit `~/.bashrc` or `~/.bash_profile`:
```bash
export JAVA_HOME="/path/to/jdk-17"
export PATH="$JAVA_HOME/bin:$PATH"
```

Then reload:
```bash
source ~/.bashrc
```

---

### Issue: Dependency Download Failures

**Symptom:**
```
[ERROR] Failed to execute goal on project: Could not resolve dependencies
```

**Solutions:**

1. **Check internet connection**
2. **Clear local repository:**
```bash
rm -rf ~/.m2/repository
mvn clean install
```

3. **Use different Maven mirror** (edit `~/.m2/settings.xml`):
```xml
<mirrors>
  <mirror>
    <id>aliyun</id>
    <mirrorOf>central</mirrorOf>
    <url>https://maven.aliyun.com/repository/central</url>
  </mirror>
</mirrors>
```

4. **Download dependencies explicitly:**
```bash
mvn dependency:resolve
mvn dependency:resolve-plugins
```

---

## 🚀 Runtime Issues

### Issue: Port 8080 Already in Use

**Symptom:**
```
***************************
APPLICATION FAILED TO START
***************************

Description:
Web server failed to start. Port 8080 was already in use.
```

**Solutions:**

**Option 1: Find and kill the process**

Windows:
```bash
netstat -ano | findstr :8080
taskkill /PID <PID> /F
```

Linux/Mac:
```bash
lsof -i :8080
kill -9 <PID>
```

**Option 2: Use different port**
```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

Or in `application.properties`:
```properties
server.port=8081
```

**Option 3: Configure dynamic port**
```properties
server.port=0  # Random available port
```

---

### Issue: Application Starts but Returns 404

**Symptom:**
```bash
curl http://localhost:8080/users
# Returns 404 Not Found
```

**Cause:** Missing context path in URL.

**Solution:**
Include the context path `/api`:
```bash
curl http://localhost:8080/api/users  # Correct
```

---

### Issue: Connection Refused

**Symptom:**
```
curl: (7) Failed to connect to localhost port 8080: Connection refused
```

**Solutions:**

1. **Verify application is running:**
```bash
# Check if process exists
ps aux | grep maven-springboot

# Check logs
tail -f logs/application.log
```

2. **Check if Spring Boot started successfully:**
Look for this in logs:
```
Started Application in X.XXX seconds
```

3. **Verify port binding:**
```bash
netstat -an | grep 8080
```

---

### Issue: Out of Memory Error

**Symptom:**
```
java.lang.OutOfMemoryError: Java heap space
```

**Solutions:**

**Increase heap size:**
```bash
# For Maven
export MAVEN_OPTS="-Xmx2048m"
mvn spring-boot:run

# For JAR
java -Xms512m -Xmx2048m -jar target/maven-springboot-multiversion.jar
```

**Monitor memory usage:**
```bash
# View JVM memory
jconsole
# or
jvisualvm
```

---

## 🧪 Testing Issues

### Issue: Tests Fail Locally but Pass in CI/CD

**Possible Causes:**
1. Different Java versions
2. Operating system differences
3. Timezone issues
4. File path separators

**Solutions:**

1. **Match Java version:**
```bash
# Check CI/CD Java version (see .github/workflows/maven-build.yml)
# Use same version locally
java -version
```

2. **Run tests with Maven:**
```bash
mvn test
# Instead of running from IDE
```

3. **Check for hardcoded paths:**
```java
// Bad
String path = "C:\\Users\\data\\file.txt";

// Good
String path = Paths.get("data", "file.txt").toString();
```

---

### Issue: JaCoCo Coverage Check Fails

**Symptom:**
```
[ERROR] Coverage checks have not been met
```

**Solutions:**

**Option 1: Lower coverage threshold**

Edit `pom.xml`:
```xml
<minimum>0.30</minimum>  <!-- Instead of 0.50 -->
```

**Option 2: Disable coverage check**
```bash
mvn install -Djacoco.skip=true
```

**Option 3: Write more tests**
```bash
# Check current coverage
mvn jacoco:report
open target/site/jacoco/index.html
```

---

### Issue: Mock Objects Not Working

**Symptom:**
```
NullPointerException when calling mocked methods
```

**Solution:**

Ensure `@MockBean` is used in Spring tests:
```java
@WebMvcTest(UserController.class)
class UserControllerTest {
    
    @MockBean  // Not @Mock
    private UserService userService;
    
    @Test
    void test() {
        when(userService.getUsers()).thenReturn(users);
        // Test code
    }
}
```

---

## 📦 Maven Issues

### Issue: Maven Not Found

**Symptom:**
```bash
mvn: command not found
```

**Solutions:**

**Check if installed:**
```bash
which mvn
echo $PATH
```

**Install Maven:**

Windows:
1. Download from https://maven.apache.org/download.cgi
2. Extract to `C:\Program Files\Apache\maven`
3. Add to PATH

Linux:
```bash
sudo apt-get install maven
```

Mac:
```bash
brew install maven
```

**Verify installation:**
```bash
mvn -version
```

---

### Issue: Checkstyle Violations Block Build

**Symptom:**
```
[ERROR] You have 333 Checkstyle violations
```

**Solutions:**

**Option 1: Fix violations**
```bash
# View violations
mvn checkstyle:check

# Generate detailed report
mvn checkstyle:checkstyle
open target/site/checkstyle.html
```

**Option 2: Disable build failure**

In `pom.xml`:
```xml
<configuration>
    <failOnViolation>false</failOnViolation>
</configuration>
```

**Option 3: Skip checkstyle**
```bash
mvn install -Dcheckstyle.skip=true
```

---

### Issue: Plugin Version Conflicts

**Symptom:**
```
[ERROR] Plugin org.apache.maven.plugins:maven-compiler-plugin
```

**Solution:**

Update plugin versions in `pom.xml`:
```bash
# Check for updates
mvn versions:display-plugin-updates

# Update all plugins
mvn versions:use-latest-releases
```

---

## ☕ Java Version Issues

### Issue: Unsupported Class File Version

**Symptom:**
```
java.lang.UnsupportedClassFileError: Bad version number in .class file
```

**Cause:** Code compiled with newer Java, running with older Java.

**Solutions:**

1. **Use correct Java version:**
```bash
java17  # If using Java 17 profile
java -jar target/maven-springboot-multiversion.jar
```

2. **Rebuild with correct profile:**
```bash
mvn clean package -Pjava11
```

---

### Issue: Features Not Available in Java 8

**Symptom:**
```
[ERROR] diamond operator is not supported in -source 1.8
```

**Solution:**

Either:
- Build with Java 11+: `mvn clean install -Pjava11`
- Avoid Java 9+ features if Java 8 support is needed

---

## 🔄 CI/CD Issues

### Issue: GitHub Actions Build Fails

**Common Causes:**

1. **Check logs in GitHub Actions tab**

2. **Test locally with same commands:**
```bash
# Same as CI/CD
mvn clean install -Pjava11
mvn test
```

3. **Check workflow file syntax:**
```bash
# Validate YAML
yamllint .github/workflows/maven-build.yml
```

---

### Issue: Actions Can't Download Dependencies

**Symptom:**
```
Failed to download dependency
```

**Solution:**

Add retry or use different Maven mirror in workflow:
```yaml
- name: Build with Maven
  run: mvn clean install --fail-at-end --retry 3
```

---

## ⚡ Performance Issues

### Issue: Slow Application Startup

**Solutions:**

1. **Disable DevTools in production:**
```properties
spring.devtools.restart.enabled=false
```

2. **Reduce logging:**
```properties
logging.level.root=WARN
```

3. **Lazy initialization:**
```properties
spring.main.lazy-initialization=true
```

---

### Issue: High Memory Usage

**Monitor and optimize:**

```bash
# Check memory usage
jmap -heap <PID>

# Dump heap for analysis
jmap -dump:format=b,file=heap.bin <PID>

# Analyze with VisualVM or Eclipse MAT
```

**Optimize:**
```bash
# Set proper heap size
java -Xms512m -Xmx1024m -jar app.jar

# Enable GC logging
java -Xlog:gc* -jar app.jar
```

---

## 🆘 Getting Help

If you can't resolve your issue:

1. **Check logs:**
```bash
tail -f logs/application.log
tail -f logs/error.log
```

2. **Enable debug logging:**
```properties
logging.level.com.example.api=DEBUG
```

3. **Create GitHub issue with:**
   - Error message
   - Steps to reproduce
   - Environment details (Java version, OS, Maven version)
   - Relevant logs

4. **Search existing issues:**
   - https://github.com/rohithpidugu/maven-springboot-multiversion/issues

---

## 📚 Additional Resources

- [Maven Troubleshooting](https://maven.apache.org/guides/mini/guide-ide-eclipse.html)
- [Spring Boot Common Issues](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html)
- [Stack Overflow - Spring Boot](https://stackoverflow.com/questions/tagged/spring-boot)

---

**Last Updated:** Day 3 - December 10, 2025
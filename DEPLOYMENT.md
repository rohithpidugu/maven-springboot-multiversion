# Deployment Guide

This guide covers various deployment strategies for the Maven Spring Boot Multi-Version API.

## 📋 Table of Contents

- [Local Development Deployment](#local-development-deployment)
- [Building for Production](#building-for-production)
- [Docker Deployment](#docker-deployment)
- [Cloud Deployment Options](#cloud-deployment-options)
- [Environment Configuration](#environment-configuration)
- [Monitoring and Maintenance](#monitoring-and-maintenance)

---

## 🖥️ Local Development Deployment

### Quick Start

```bash
# Clone the repository
git clone https://github.com/rohithpidugu/maven-springboot-multiversion.git
cd maven-springboot-multiversion

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### Running with Specific Java Version

```bash
# Java 8
mvn clean install -Pjava8
mvn spring-boot:run -Pjava8

# Java 11 (Default)
mvn clean install -Pjava11
mvn spring-boot:run -Pjava11

# Java 17
mvn clean install -Pjava17
mvn spring-boot:run -Pjava17
```

### Custom Port Configuration

```bash
# Run on different port
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081

# Or set in application.properties
server.port=8081
```

---

## 📦 Building for Production

### Creating Executable JAR

```bash
# Build with all tests
mvn clean package

# Build without tests (faster)
mvn clean package -DskipTests

# Build with specific Java profile
mvn clean package -Pjava11
```

**Output:** `target/maven-springboot-multiversion.jar`

### Running the JAR

```bash
# Basic execution
java -jar target/maven-springboot-multiversion.jar

# With custom port
java -jar target/maven-springboot-multiversion.jar --server.port=8081

# With specific profile
java -jar target/maven-springboot-multiversion.jar --spring.profiles.active=prod

# With JVM options
java -Xms512m -Xmx2048m -jar target/maven-springboot-multiversion.jar
```

### Production Build Checklist

- [ ] Run all tests: `mvn test`
- [ ] Check code quality: `mvn checkstyle:check`
- [ ] Verify coverage: `mvn jacoco:report`
- [ ] Build without errors: `mvn clean package`
- [ ] Test the JAR locally
- [ ] Review application logs
- [ ] Verify all endpoints work

---

## 🐳 Docker Deployment

### Dockerfile

Create a `Dockerfile` in the project root:

```dockerfile
# Multi-stage build for smaller image

# Stage 1: Build
FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:11-jre
WORKDIR /app
COPY --from=build /app/target/maven-springboot-multiversion.jar app.jar

# Create non-root user
RUN useradd -m appuser
USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Docker Commands

```bash
# Build Docker image
docker build -t maven-springboot-api:latest .

# Run container
docker run -p 8080:8080 maven-springboot-api:latest

# Run in background
docker run -d -p 8080:8080 --name springboot-api maven-springboot-api:latest

# View logs
docker logs -f springboot-api

# Stop container
docker stop springboot-api

# Remove container
docker rm springboot-api
```

### Docker Compose

Create `docker-compose.yml`:

```yaml
version: '3.8'

services:
  api:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=prod
      - SERVER_PORT=8080
    volumes:
      - ./logs:/app/logs
    restart: unless-stopped
    healthcheck:
      test: ["CMD", "curl", "-f", "http://localhost:8080/api/actuator/health"]
      interval: 30s
      timeout: 10s
      retries: 3
      start_period: 40s
```

**Run with Docker Compose:**

```bash
# Start services
docker-compose up -d

# View logs
docker-compose logs -f

# Stop services
docker-compose down
```

---

## ☁️ Cloud Deployment Options

### 1. AWS Elastic Beanstalk

```bash
# Install AWS EB CLI
pip install awsebcli

# Initialize EB application
eb init -p java-11 maven-springboot-api

# Create environment
eb create production-env

# Deploy
eb deploy

# Open application
eb open
```

### 2. Heroku

```bash
# Install Heroku CLI
# Download from: https://devcenter.heroku.com/articles/heroku-cli

# Login
heroku login

# Create app
heroku create maven-springboot-api

# Deploy
git push heroku master

# Open app
heroku open

# View logs
heroku logs --tail
```

**Create `Procfile`:**
```
web: java -Dserver.port=$PORT -jar target/maven-springboot-multiversion.jar
```

### 3. Azure App Service

```bash
# Install Azure CLI
# Download from: https://docs.microsoft.com/cli/azure/install-azure-cli

# Login
az login

# Create resource group
az group create --name myResourceGroup --location eastus

# Create App Service plan
az appservice plan create --name myPlan --resource-group myResourceGroup --sku B1 --is-linux

# Create web app
az webapp create --resource-group myResourceGroup --plan myPlan --name maven-springboot-api --runtime "JAVA|11-java11"

# Deploy JAR
az webapp deploy --resource-group myResourceGroup --name maven-springboot-api --src-path target/maven-springboot-multiversion.jar --type jar
```

### 4. Google Cloud Platform (Cloud Run)

```bash
# Install gcloud CLI
# Download from: https://cloud.google.com/sdk/docs/install

# Login
gcloud auth login

# Set project
gcloud config set project YOUR_PROJECT_ID

# Build and push to Container Registry
gcloud builds submit --tag gcr.io/YOUR_PROJECT_ID/maven-springboot-api

# Deploy to Cloud Run
gcloud run deploy maven-springboot-api \
  --image gcr.io/YOUR_PROJECT_ID/maven-springboot-api \
  --platform managed \
  --region us-central1 \
  --allow-unauthenticated
```

---

## ⚙️ Environment Configuration

### Application Profiles

Create profile-specific properties files:

**`application-dev.properties`** (Development):
```properties
server.port=8080
logging.level.com.example.api=DEBUG
spring.devtools.restart.enabled=true
```

**`application-prod.properties`** (Production):
```properties
server.port=80
logging.level.root=WARN
logging.level.com.example.api=INFO
spring.devtools.restart.enabled=false
```

### Environment Variables

```bash
# Set environment variables
export SPRING_PROFILES_ACTIVE=prod
export SERVER_PORT=8080
export JAVA_OPTS="-Xms512m -Xmx2048m"

# Run with environment variables
java $JAVA_OPTS -jar target/maven-springboot-multiversion.jar
```

### Externalized Configuration

Create `application.yml` for production:

```yaml
server:
  port: ${SERVER_PORT:8080}
  servlet:
    context-path: /api

spring:
  application:
    name: maven-springboot-multiversion

logging:
  level:
    root: ${LOG_LEVEL:INFO}
    com.example.api: ${APP_LOG_LEVEL:DEBUG}
  file:
    name: logs/application.log

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
```

---

## 📊 Monitoring and Maintenance

### Health Checks

```bash
# Check application health
curl http://localhost:8080/api/actuator/health

# Expected response
{
  "status": "UP"
}
```

### Metrics Endpoint

```bash
# View application metrics
curl http://localhost:8080/api/actuator/metrics

# Specific metric
curl http://localhost:8080/api/actuator/metrics/jvm.memory.used
```

### Log Management

**Location:** `logs/application.log` and `logs/error.log`

**Rotate logs:**
```bash
# View recent logs
tail -f logs/application.log

# Search for errors
grep "ERROR" logs/error.log

# View last 100 lines
tail -n 100 logs/application.log
```

### Performance Monitoring

**JVM Monitoring:**
```bash
# JVM memory usage
java -XX:+PrintFlagsFinal -jar target/maven-springboot-multiversion.jar

# Enable JMX
java -Dcom.sun.management.jmxremote \
     -Dcom.sun.management.jmxremote.port=9010 \
     -Dcom.sun.management.jmxremote.authenticate=false \
     -Dcom.sun.management.jmxremote.ssl=false \
     -jar target/maven-springboot-multiversion.jar
```

### Graceful Shutdown

```bash
# Send SIGTERM for graceful shutdown
kill -15 <PID>

# Configure shutdown timeout (application.properties)
server.shutdown=graceful
spring.lifecycle.timeout-per-shutdown-phase=30s
```

---

## 🔒 Security Considerations

### Production Checklist

- [ ] Use HTTPS in production
- [ ] Disable DevTools in production
- [ ] Set secure logging levels (WARN/ERROR)
- [ ] Configure proper CORS settings
- [ ] Use environment variables for secrets
- [ ] Enable actuator security
- [ ] Set up rate limiting
- [ ] Configure firewall rules
- [ ] Regular security updates

### Securing Actuator Endpoints

Add to `application-prod.properties`:
```properties
management.endpoints.web.exposure.include=health,info
management.endpoint.health.show-details=never
```

---

## 🚀 Continuous Deployment

### GitHub Actions Auto-Deploy

See `.github/workflows/maven-build.yml` for CI/CD pipeline.

**Automated deployment triggers:**
- Push to `master` branch
- All tests pass
- Build succeeds for all Java versions
- Code quality checks pass

### Manual Deployment Script

Create `deploy.sh`:

```bash
#!/bin/bash

echo "Starting deployment..."

# Build
echo "Building application..."
mvn clean package -DskipTests

# Stop existing instance
echo "Stopping existing instance..."
pkill -f maven-springboot-multiversion.jar

# Start new instance
echo "Starting new instance..."
nohup java -jar target/maven-springboot-multiversion.jar > /dev/null 2>&1 &

echo "Deployment complete!"
echo "Application running on port 8080"
```

**Make executable and run:**
```bash
chmod +x deploy.sh
./deploy.sh
```

---

## 🔧 Troubleshooting

### Common Issues

**Port Already in Use:**
```bash
# Find process using port 8080
netstat -ano | findstr :8080  # Windows
lsof -i :8080                 # Linux/Mac

# Kill process
kill -9 <PID>
```

**Out of Memory:**
```bash
# Increase heap size
java -Xms1g -Xmx2g -jar target/maven-springboot-multiversion.jar
```

**Application Won't Start:**
```bash
# Check logs
cat logs/application.log

# Verify Java version
java -version

# Test JAR integrity
java -jar target/maven-springboot-multiversion.jar --version
```

---

## 📞 Support

For deployment issues:
- Check logs: `logs/application.log`
- Review health: `/api/actuator/health`
- GitHub Issues: [Create an issue](https://github.com/rohithpidugu/maven-springboot-multiversion/issues)

---

**Last Updated:** Day 3 - December 10, 2025
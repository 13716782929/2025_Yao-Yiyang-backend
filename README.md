# CoinApi-backend


---

### Project Overview

This project is a Spring Boot-based REST API service that calculates the minimum number of coins required to achieve a given target amount. The application includes the following features:

1. **Calculate Minimum Coins**: Users can send a POST request with the target amount and a list of coin denominations. The service responds with the optimal coin combination.
2. **Input Validation**: The API validates invalid target amounts and coin denominations, returning appropriate error responses.
3. **Unit Testing**: JUnit tests are implemented to ensure the functionality is robust and accurate.

---

### Steps to Build and Run the Docker Container

#### 1. **Write the Dockerfile**

The `Dockerfile` uses OpenJDK as the base image, builds the Spring Boot application using Gradle, and runs the generated JAR file. The `Dockerfile` is as follows:

```dockerfile
# Use OpenJDK base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Gradle wrapper and build files
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# Make the Gradle wrapper executable
RUN chmod +x ./gradlew

# Build the project using Gradle, skipping tests
RUN ./gradlew build -x test

# Copy the generated JAR file to the container
COPY build/libs/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

#### 2. **Build the Docker Image**

Run the following command from the project root directory to build the Docker image:

```bash
docker build -t coin-api-service .
```

This command:
- Uses the `openjdk:17-jdk-slim` image as the base.
- Copies the Gradle files and source code into the container.
- Executes the Gradle build command to generate the Spring Boot JAR file.
- Packages the application into the image.

---

#### 3. **Run the Docker Container**

Run the built container with the following command:

```bash
docker run -p 8080:8080 coin-api-service
```

This command:
- Maps port `8080` in the container to port `8080` on the host machine.
- Starts the REST API service, making it accessible via `http://localhost:8080`.

---

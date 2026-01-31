# -------- Stage 1: Builder --------
# Use Maven + Java 21 (Eclipse Temurin base image)
FROM maven:3.9.9-eclipse-temurin-21 AS builder

# Set working directory inside container
WORKDIR /app

# Copy pom.xml first (so dependencies can be cached)
COPY pom.xml .

# Download all dependencies (offline mode for faster builds later)
RUN mvn dependency:go-offline -B

# Copy source code into container
COPY src ./src

# Build the project and create JAR file
RUN mvn clean package


# -------- Stage 2: Runner --------
# Use Eclipse Temurin Java 21 runtime
FROM eclipse-temurin:21-jdk AS runner

# Set working directory again
WORKDIR /app

# Copy JAR file from builder stage into runner stage
COPY --from=builder ./app/target/patientService-0.0.1-SNAPSHOT.jar ./app.jar

# Expose port 4000 (application will run here)
EXPOSE 4000

# Command to run the JAR file when container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
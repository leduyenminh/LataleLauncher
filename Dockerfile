# =========================
# Stage 1: Build JAR
# =========================
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Set working dir
WORKDIR /app

# Copy only pom.xml first (for dependency caching)
COPY pom.xml .

# Download dependencies (better caching)
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# =========================
# Stage 2: Runtime
# =========================
FROM eclipse-temurin:17-jre-alpine

# Create app directory
WORKDIR /app

# Set environment variables (can be overridden at runtime)
ENV JAVA_OPTS="-Xms256m -Xmx512m" \
    TZ=UTC

# Copy JAR from builder stage
COPY --from=builder /app/target/springLataleLauncher-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8080

# Run application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

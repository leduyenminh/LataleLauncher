# =========================
# Stage 1: Build JAR
# =========================
FROM maven:3.9.9-eclipse-temurin-21 AS builder

# Set working dir
WORKDIR /app

# Copy only pom.xml first (for dependency caching)
COPY pom.xml .

COPY settings.xml /root/.m2/settings.xml
# Download dependencies (better caching)
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# =========================
# Stage 2: Runtime
# =========================
FROM openjdk:21-jdk AS runner

# Create app directory
WORKDIR /app

# Set environment variables (can be overridden at runtime)
ENV JAVA_OPTS="java -XX:+UseZGC -Xms256m -Xmx1g -XX:MaxRAMPercentage=70.0 \
                 -Xlog:gc*:file=/var/log/myapp/gc-zgc.log:time,uptime,level:filecount=3,filesize=50M \
                 -jar app.jar " \
    TZ=UTC

# Copy JAR from builder stage
COPY --from=builder /app/target/LauncherService-1.0-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8080

# Run application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

# Start from an OpenJDK image
FROM openjdk:17-jdk-alpine

# Set environment variables
ENV JAVA_OPTS=""

# Set the working directory inside the container
WORKDIR /app

# Optionally copy Liquibase files if needed for migration at container start
COPY src/main/resources/liquibase /app/liquibase

# Copy the JAR file
COPY target/springLataleLauncher-0.0.1-SNAPSHOT.jar app.jar

# Expose the app port (update based on your app)
EXPOSE 8080

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

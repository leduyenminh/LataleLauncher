FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package

FROM openjdk:21-jdk AS runner

WORKDIR /app

# Set environment variables (can be overridden at runtime)
ENV JAVA_OPTS="-XX:+UseZGC -Xms512m -Xmx1g -XX:MaxRAMPercentage=80.0 \
  -Xlog:gc*:file=/var/log/myapp/gc-zgc.log:time,uptime,level:filecount=3,filesize=50M" \
    TZ=UTC

COPY --from=builder ./app/target/api-gateway-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 4004

ENTRYPOINT ["java", "-jar", "app.jar"]
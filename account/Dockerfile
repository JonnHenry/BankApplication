# Stage 1: Build the application using Maven + Java 11
FROM maven:3.9.3-eclipse-temurin-11 AS build
WORKDIR /app

COPY --chown=maven:maven pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the final image
FROM eclipse-temurin:11-jdk
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app.jar"]

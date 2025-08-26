# Stage 1: Build the application using Maven + Java 11
FROM maven:3.9.3-eclipse-temurin-11 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Crear la imagen final para ejecutar la aplicación
FROM eclipse-temurin:11-jdk
WORKDIR /app

# Copiar el jar compilado desde la etapa build
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto de la aplicación
EXPOSE 8001

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

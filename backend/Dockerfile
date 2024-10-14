# Use Maven for the build stage
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Set the working directory in the container for the build
WORKDIR /app

# Copy the pom.xml and download project dependencies (this step is cached)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of the project files
COPY src ./src

# Package the application (skip tests if needed)
RUN mvn clean package -DskipTests

# Use a lightweight OpenJDK runtime to run the app
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/ecommerce-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port that the app runs on
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Use Java 17 image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy jar file
COPY target/*.jar app.jar

# Expose port (Spring Boot default)
EXPOSE 8081

# Run application
ENTRYPOINT ["java","-jar","app.jar"]
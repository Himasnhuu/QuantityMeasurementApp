FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy all project files
COPY . .

# Build JAR
RUN mvn clean package -DskipTests

# --- Runtime Stage ---
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy built jar from previous stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "app.jar"]
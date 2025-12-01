# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml first
COPY pom.xml .

# Copy the actual source folder
COPY HelpingHeart/src ./src

# Build the jar
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=build /app/target/HelpingHeart-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]

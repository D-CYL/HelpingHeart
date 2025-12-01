FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

CMD ["java", "-jar", "target/*.jar"]

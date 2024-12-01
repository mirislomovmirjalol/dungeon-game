# Build Stage
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run Stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/dungeon-game-1.0.0.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
# Build Stage
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY src /app
RUN mvn clean package -DskipTests

# Run Stage
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/stock-0.0.1-SNAPSHOT.jar stock-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","stock-0.0.1-SNAPSHOT.jar"]


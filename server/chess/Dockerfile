FROM maven:3.8.4-openjdk-17-slim AS build-stage
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM openjdk:17 AS run-stage
WORKDIR /app
COPY --from=build-stage /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
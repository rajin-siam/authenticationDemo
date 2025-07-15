# Use an official Java runtime as base image
FROM maven:3.8.4-openjdk-17 AS build

# Add a label (optional)
LABEL maintainer="Md. Rajin Mashrur Siam"


# Set the working directory inside the container
WORKDIR /app


COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests


FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/authenticationDemo-0.0.1-SNAPSHOT.jar .

EXPOSE 8081
# Copy the built JAR file into the container

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/authenticationDemo-0.0.1-SNAPSHOT.jar"]

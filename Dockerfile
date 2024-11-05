# Stage 1: Build & copy the Go source code
FROM gradle:jdk-21-and-23-alpine AS build

# Set working directory inside the container
WORKDIR /app

# Copy application code into the container
COPY src src
COPY . .

# Build SpringBoot app
RUN gradle build

# Stage 2: Create production image
FROM openjdk:21-slim

RUN apt update && apt -y install curl

# Set working directory inside the new image
WORKDIR /app

# Expose port if your app requires it
EXPOSE 9999

# Copy the compiled binary from the build stage
COPY --from=build /app/build/libs/java-time-service-0.0.1-SNAPSHOT.jar /app/java-time-service.jar

# Make the executable run in foreground by default. This is recommended.
ENTRYPOINT ["java", "-jar",  "java-time-service.jar"]

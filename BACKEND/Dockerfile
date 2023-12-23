# Use a base image with Java and Maven installed
FROM adoptopenjdk:17-jdk-hotspot AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven POM file to the container
COPY pom.xml .

# Copy the entire project (except for the excluded items) to the container
COPY src ./src

# Build the application
RUN ./mvnw package -DskipTests

# Use a smaller base image for the application runtime
FROM adoptopenjdk:17-jre-hotspot

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the build stage to the runtime image
COPY --from=build /app/target/admin-0.0.1-SNAPSHOT.jar ./app.jar

# Expose the port your app runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]

# Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled JAR or class files
COPY target/sensorNode-1.0-SNAPSHOT.jar app.jar

# Set any environment variables if needed (optional)
ENV SERVER_URL ws://sensor-server:8081/sensor
# Run the console application
ENTRYPOINT ["java", "-jar", "app.jar"]
# Use a Java-enabled Alpine Linux as the base image
FROM openjdk:22-ea-17-slim

# Create a volume for temporary files
VOLUME /tmp

# Copy the jar file from the target directory to the image
COPY jos-0.0.1-SNAPSHOT.jar jos-0.0.1-SNAPSHOT.jar


# Expose the port
EXPOSE 8080

# Define the entrypoint to run the jar file
ENTRYPOINT ["java","-jar","/jos-0.0.1-SNAPSHOT.jar"]

# Use a base image with Java installed
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory in the container
WORKDIR /unit-conversion-factor

# Copy the JAR file into the container
COPY target/unitconversionfactor-0.0.1-SNAPSHOT.jar unit-conversion-factor.jar

# Expose the port on which your Spring Boot application runs
EXPOSE 8081

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "unit-conversion-factor.jar"]

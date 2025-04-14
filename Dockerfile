# Use the official OpenJDK image from the Docker Hub
FROM openjdk:17-jdk-alpine

# The application's jar file
ARG JAR_FILE=target/sage-data-commons-dashboard-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
COPY ${JAR_FILE} /app.jar

# Copy all JSON files into the container's specified directory
#COPY /path/to/local-dashboard/*.json /usr/local/dashboard/data/

# Expose the debug port inside the container
EXPOSE 5005

# Set environment variable for Spring configuration location
ENV SPRING_CONFIG_LOCATION=file:/usr/local/dashboard/conf/queueapp.properties

# Run the jar file with the environment variable
#ENTRYPOINT ["java","-Dspring.config.location=${SPRING_CONFIG_LOCATION}","-jar","/app.jar"]

# Enable remote debugging for the JVM
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-Dspring.config.location=${SPRING_CONFIG_LOCATION}", "-jar", "/app.jar"]


# docker build -t sage-data-commons-dashboard .
# docker run --detach -p 9090:8080 -v <localpath>/queueapp.properties:/usr/local/dashboard/queueapp.properties sage-data-commons-dashboard
# docker run --detach --name sage-data-commons-dashboard -p 9090:8080 -v <localpath>/queueapp.properties:/usr/local/dashboard/queueapp.properties sage-data-commons-dashboard

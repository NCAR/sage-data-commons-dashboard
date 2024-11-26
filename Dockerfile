# Use the official OpenJDK image from the Docker Hub
FROM openjdk:17-jdk-alpine

# The application's jar file
ARG JAR_FILE=target/sage-data-commons-dashboard-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
COPY ${JAR_FILE} /app.jar

# Copy the local file to the container, where the properties file points (testing)
COPY ./src/main/resources/casper_qstat_queue.json /tmp/casper_qstat_queue.json
COPY ./src/main/resources/casper_qstat_jobs.json /tmp/casper_qstat_jobs.json

# Set environment variable for Spring configuration location
ENV SPRING_CONFIG_LOCATION=file:/usr/local/dashboard/queueapp.properties

# Run the jar file with the environment variable
ENTRYPOINT ["java","-Dspring.config.location=${SPRING_CONFIG_LOCATION}","-jar","/app.jar"]

# docker build -t sage-data-commons-dashboard .
# docker run --detach -p 9090:8080 -v /Users/cgrant/dashboard/queueapp.properties:/usr/local/dashboard/queueapp.properties sage-data-commons-dashboard
# docker run --detach --name sage-data-commons-dashboard -p 9090:8080 -v /Users/cgrant/dashboard/queueapp.properties:/usr/local/dashboard/queueapp.properties sage-data-commons-dashboard

FROM eclipse-temurin:17.0.15_6-jre-noble

LABEL org.opencontainers.image.description="Sage Data Commons Dashboard"
LABEL org.opencontainers.image.source="https://github.com/NCAR/sage-data-commons-dashboard"

ARG APP_JAR="target/ROOT.jar"

COPY $APP_JAR /opt/app/lib/app.jar

RUN mkdir /opt/app/conf /opt/app/data

EXPOSE 8080

CMD ["java", "-jar", "/opt/app/lib/app.jar", "--spring.config.additional-location=file:/opt/app/conf/"]

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar rent-house.jar
ENTRYPOINT ["java", "-Xms128M ", "-Xmx256M", "-jar", "your-precious-service-1.0.0.jar"]

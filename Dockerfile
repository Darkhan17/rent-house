FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar rent-house.jar
ENTRYPOINT ["java", "-Xms128M ", "-Xmx256M", "-jar", "rent-house-0.0.1.jar"]

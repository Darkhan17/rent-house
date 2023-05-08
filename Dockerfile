FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar rent-house.jar
ENTRYPOINT ["java","-jar","-Xmx512m /rent-house.jar"]
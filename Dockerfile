FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar rent-house.jar
ENTRYPOINT ["java","-jar","-Xms512m -Xmx1024m /rent-house.jar"]
FROM openjdk:11-jdk-slim


WORKDIR /app

COPY src /app/src
COPY pom.xml /app/pom.xml

RUN mvn clean install -DskipTests

COPY target/ws-0.0.1-SNAPSHOT.jar ws-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar","ws-0.0.1-SNAPSHOT.jar"]

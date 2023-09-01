FROM openjdk:11-jdk-slim


WORKDIR /app

COPY src /app/src
COPY pom.xml /app/pom.xml

COPY target/sp-0.0.1-SNAPSHOT.jar sp-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar","sp-0.0.1-SNAPSHOT.jar"]

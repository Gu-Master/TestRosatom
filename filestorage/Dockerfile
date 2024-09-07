
FROM openjdk:21-jdk-slim

WORKDIR /app

COPY build/libs/filestorage-0.0.1-SNAPSHOT.jar /app/filestorage.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/filestorage.jar"]

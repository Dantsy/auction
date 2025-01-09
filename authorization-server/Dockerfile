FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/authorization-server-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8085

CMD ["java", "-jar", "app.jar"]
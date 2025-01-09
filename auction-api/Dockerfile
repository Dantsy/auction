FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/auction-api-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8088

CMD ["java", "-jar", "app.jar"]
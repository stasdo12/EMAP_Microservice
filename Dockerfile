FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target.EPAMT1Microservice-0.0.1-SNAPSHOT.jar microservice.jar
EXPOSE 8081
CMD ["java",  "-jar", "microservice.jar"]


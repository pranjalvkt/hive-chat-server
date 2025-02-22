FROM eclipse-temurin:17-jdk-alpine
LABEL authors="Pranjal Tripathi"
WORKDIR /app
COPY target/hive-chat-server-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/hive-chat-server.jar"]
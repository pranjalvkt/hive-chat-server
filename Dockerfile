FROM openjdk:21-oracle
LABEL authors="Pranjal Tripathi"
WORKDIR /hive-chat-server
COPY target/hive-chat-server-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
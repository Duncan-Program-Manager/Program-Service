FROM openjdk:11 as rabbitmq
EXPOSE 8084 3306
ADD target/Program-API-0.0.1-SNAPSHOT.jar programapi-docker.jar
ENTRYPOINT ["java","-jar","programapi-docker.jar"]
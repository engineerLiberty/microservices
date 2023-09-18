FROM openjdk:17
EXPOSE 8080
ADD target/microservice.jar microservice.jar
ENTRYPOINT ["java", "-jar", "/microservice.jar"]
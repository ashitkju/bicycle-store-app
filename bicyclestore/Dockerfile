FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/BicycleStore-0.0.1-SNAPSHOT.jar bicycle.jar
ENTRYPOINT ["java", "-jar", "/bicycle.jar"]
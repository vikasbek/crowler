FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/crowler.jar
COPY ${JAR_FILE} .
EXPOSE 8080 8081 8082 80
ENTRYPOINT ["java","-jar","./crowler.jar"]

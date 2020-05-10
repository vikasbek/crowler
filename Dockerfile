FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/crowler.jar
COPY ${JAR_FILE} .
ENTRYPOINT ["java","-jar","./crowler.jar"]

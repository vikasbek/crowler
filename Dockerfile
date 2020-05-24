FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/crowler.jar
COPY ${JAR_FILE} .
EXPOSE 8082
ENTRYPOINT ["java","-jar","./crowler.jar"]

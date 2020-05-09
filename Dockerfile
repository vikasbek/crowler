FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN apt-get update -y
RUN sudo apt-get install docker.io
ENTRYPOINT ["java","-jar","/app.jar"]

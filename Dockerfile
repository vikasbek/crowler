FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/crowler.jar
ENTRYPOINT ["java","-jar","/crowler.jar"]

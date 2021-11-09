FROM openjdk:8-jdk-alpine
EXPOSE 8083
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
CMD  ["java","-jar","app.jar"]

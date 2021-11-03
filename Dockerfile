FROM openjdk:11
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
CMD  ["java","-jar","app.jar"]

FROM openjdk:8-jdk-alpine
EXPOSE 8082
ADD /target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-1.5.jar app.jar
ENTRYPOINT [ "java", "-jar"	 , "/app.jar"]
FROM openjdk:8
EXPOSE 8083
ADD /target/timesheet-1.0.jar timesheet-1.0.jar
ENTRYPOINT ["java","-jar","timesheet-1.0.jar"]
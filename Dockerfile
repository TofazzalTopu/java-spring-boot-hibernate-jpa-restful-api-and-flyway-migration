FROM openjdk:1.8
EXPOSE 8080
ADD target/user-progress.jar user-progress.jar
ENTRYPOINT ["java", "-jar", "/user-progress.jar"]

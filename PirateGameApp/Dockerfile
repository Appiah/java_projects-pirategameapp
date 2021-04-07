FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD build/libs/PirateGameApp-0.1-runner.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
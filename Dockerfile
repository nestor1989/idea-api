FROM openjdk:17-jdk-slim
ARG JAR_FILE=build/libs/idea_api-0.0.1.jar
COPY ${JAR_FILE} idea-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "idea-api.jar"]

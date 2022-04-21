FROM adoptopenjdk/openjdk11:alpine
ARG JAR_FILE=target/registry-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar", "app.jar"]
EXPOSE 8888
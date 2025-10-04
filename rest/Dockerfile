FROM eclipse-temurin:21-jdk-alpine
LABEL authors="goncaloroseiro"

WORKDIR /app
COPY target/rest-*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
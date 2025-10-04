FROM eclipse-temurin:21-jdk-alpine
LABEL authors="goncaloroseiro"

WORKDIR /app
COPY target/calculator-*jar app.jar

EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/app.jar"]
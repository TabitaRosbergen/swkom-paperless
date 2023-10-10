FROM openjdk:21
MAINTAINER wir
WORKDIR /app
COPY target/openapi-spring-1.0.jar /app/openapi-spring-1.0.jar
ENTRYPOINT ["java","-jar","openapi-spring-1.0.jar"]
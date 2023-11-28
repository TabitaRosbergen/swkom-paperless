FROM amazoncorretto:21-alpine-jdk
WORKDIR /app

RUN apk update

# TODO should be it's own container
# Install tesseract library
RUN apk add --no-cache tesseract-ocr
# Check the installation status
RUN tesseract --list-langs
RUN tesseract -v

# Install Maven (if not already installed)
RUN apk --no-cache add maven
# copy sourcecode
COPY . /app
# Build the application --> skit tests for now
RUN mvn package -DskipTests

EXPOSE 8088
ENTRYPOINT ["java","-jar","target/openapi-spring-1.0.jar"]
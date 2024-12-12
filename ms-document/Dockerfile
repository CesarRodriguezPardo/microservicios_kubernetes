FROM --platform=linux/amd64 openjdk:22
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-document.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/ms-document.jar"]
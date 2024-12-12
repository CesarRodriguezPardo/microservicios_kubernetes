FROM --platform=linux/amd64 openjdk:22
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} gateway-server.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/gateway-server.jar"]
FROM emst/adoptopenjdk-11-jdk-openj9-base:latest

RUN mkdir -p /opt/service/config
COPY target/*.jar /opt/service/
WORKDIR /opt/service

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar *.jar"]

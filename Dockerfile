FROM emst/ubi9-openjdk-11-base:latest

RUN mkdir -p /opt/service/config
COPY target/*.jar /opt/service/
WORKDIR /opt/service

EXPOSE 8080

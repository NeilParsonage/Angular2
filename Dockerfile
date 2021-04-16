FROM @container-registry@/emst/dc-java-base:latest

RUN mkdir -p /opt/service/config
COPY *.jar /opt/service/
WORKDIR /opt/service

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -Xmx128m -Djava.security.egd=file:/dev/./urandom -jar *.jar"]

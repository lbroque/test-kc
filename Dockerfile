FROM maven:3.6.3-openjdk-8-slim  as build-stage

WORKDIR /home/core
#COPY pom.xml /home/core/
#COPY src /home/core/src
COPY target /home/core/target

#RUN mvn --version && ls -al /home/core/*
#RUN mvn clean compile install

FROM openjdk:8-jre-alpine as deploy-stage

COPY --from=build-stage /home/core/target/kc-thorntail.jar /opt/

COPY target/kc-thorntail.jar /opt/kc.jar

EXPOSE 8080
ENTRYPOINT ["/usr/bin/java", "-jar", "-Djava.net.preferIPv4Stack=true", "-Dthorntail.logging=DEBUG"]
CMD ["/opt/kc-thorntail.jar"]

# Base Alpine Linux based image with OpenJDK JRE only
FROM openjdk:8-jre-alpine
MAINTAINER Richard Chen
# copy application JAR
COPY ./target/json2protobuf-1.0-SNAPSHOT.jar /json2protobuf-1.0-SNAPSHOT.jar
# expose port 8080
EXPOSE 8080
# specify default command
CMD ["/usr/bin/java", "-jar", "/json2protobuf-1.0-SNAPSHOT.jar"]

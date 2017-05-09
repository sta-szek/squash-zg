FROM openjdk:latest
MAINTAINER Piotr Joński p.jonski@pojo.pl

COPY application.jar application.jar
RUN chmod +x application.jar
RUN ls -al /
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]
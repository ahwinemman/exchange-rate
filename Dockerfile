FROM adoptopenjdk/openjdk11:ubi

MAINTAINER ahwinrukevwe@gmail.com

ARG JAR_FILE=target/exchange-rate-0.0.1.jar
ADD ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
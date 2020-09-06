FROM openjdk:11-jre
LABEL maintainer="nenadmit"
WORKDIR /srv/app
ARG JAR_FILE=target/my-homemade-shop.api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} my-app.jar
ENTRYPOINT ["java","-jar","my-app.jar"]
EXPOSE 8082


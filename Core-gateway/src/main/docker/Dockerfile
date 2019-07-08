FROM java:8-jdk-alpine

COPY gateway.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch gateway.jar'

ENTRYPOINT ["java","-jar","gateway.jar"]

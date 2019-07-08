FROM java:8-jdk-alpine

COPY languange.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch languange.jar'

ENTRYPOINT ["java","-jar","languange.jar"]

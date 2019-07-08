FROM java:8-jdk-alpine

COPY languange-web.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch languange-web.jar'

ENTRYPOINT ["java","-jar","languange-web.jar"]

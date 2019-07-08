FROM java:8-jdk-alpine

COPY security.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch security.jar'

ENTRYPOINT ["java","-jar","security.jar"]

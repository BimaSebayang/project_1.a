FROM java:8-jdk-alpine

COPY security-web.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch security-web.jar'

ENTRYPOINT ["java","-jar","security-web.jar"]

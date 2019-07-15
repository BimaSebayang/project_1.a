FROM java:8-jdk-alpine

COPY twilight-core-gateway.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch twilight-core-gateway.jar'

ENTRYPOINT ["java","-jar","twilight-core-gateway.jar"]

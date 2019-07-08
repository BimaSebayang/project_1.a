FROM java:8-jdk-alpine

COPY twilight-town.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch twilight-town.jar'

ENTRYPOINT ["java","-jar","twilight-town.jar"]

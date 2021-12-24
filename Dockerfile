FROM openjdk:11

ARG ARTIFACT_VERSION=0.0.1-SNAPSHOT

WORKDIR /app

COPY ./build/libs/consignment-${ARTIFACT_VERSION}.jar /app/app.jar
COPY ./scripts/start.sh /app/start.sh

RUN chmod +x /app/start.sh

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "/app/start.sh"]
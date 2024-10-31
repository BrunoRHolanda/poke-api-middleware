FROM azul/zulu-openjdk:17.0.5-17.38.21-jre AS build

MAINTAINER "Bruno R. Holanda"

WORKDIR /app

COPY . .

ENTRYPOINT ["tail", "-f", "/dev/null"]

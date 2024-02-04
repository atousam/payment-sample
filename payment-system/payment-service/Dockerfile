FROM ubuntu:latest
RUN apt-get update && apt-get install -y \
    wget \
    && rm -rf /var/lib/apt/lists/*

RUN apt install openjdk-17-jdk

WORKDIR /home/files/mock/

COPY wait-for-it.sh .
RUN chmod +x wait-for-it.sh

COPY payment.sh .
RUN chmod +x payment.sh

COPY target/payment-service-1.0-SNAPSHOT.jar payment-service-1.0-SNAPSHOT.jar
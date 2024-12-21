# Spring Boot R2DBC Example

An example implementation of Spring Boot R2DBC REST API with PostgreSQL database.

## Technology stack
* Java 21
* Maven
* PostgreSQL 17
* Docker

## Java Dependencies
* Spring Boot 3.4.1
* Spring Data R2DBC
* R2DBC PostgreSQL Driver
* JUnit 5

## Setup

1. Run postgresql locally (or start from docker-compose file)
2. Create two databases
   - postgres (main database)
   - test (dummy database for integration testing)
3. Run Spring Boot App

## Building Docker Image

1. Run spring-boot build image plugin

`mvn spring-boot:build-image -DskipTests`

2. Run generated docker image

`docker run -it -p8080:8080 --network="host" reactive-postgres:0.0.2-SNAPSHOT`





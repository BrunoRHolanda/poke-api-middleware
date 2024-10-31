# poke-api-middleware

This is a middleware for the [PokeAPI](https://pokeapi.co/). It is written in Java and uses the Spring Boot framework.

## Installation

1. Clone the repository
2. Install the java jre 17 zulu version
3. Install the docker
4. run the command `docker compose up -d` to start the redis server
5. run the command `./gradlew bootRun` to start the application`

## Swagger

The swagger documentation can be accessed at `http://localhost:8000/swagger-ui.html`

## Endpoints

The following endpoints are available:

1. `api/v1/pokemon/{name}` - Get a pokemon by name and your abilities sorted by name

## Tests

To run the tests, run the command `./gradlew test`

## Coverage

To generate the coverage report, run the command `./gradlew test jacocoTestReport`

The coverage report can be found at `build/reports/jacoco/test/html/index.html`

## Documentation

To generate the documentation, run the command `./gradlew javadoc`

The documentation can be found at `{module}/build/docs/javadoc/index.html`

## Build

To build the application, run the command `./gradlew bootJar`

The jar file can be found at `build/libs/application.jar`

[![Clean Build and create release on push to Main](https://github.com/yara-digitalproduction/quarkus-microservice-starter-lambda/actions/workflows/create-release.yml/badge.svg?branch=main)](https://github.com/yara-digitalproduction/quarkus-microservice-starter-lambda/actions/workflows/create-release.yml)
# Quarkus Microservice AWS Lambda Starter Project
This project contains a Quarkus starter application to easily get started with developing microservices using Quarkus. 

## Built with
* [Quarkus](https://quarkus.io)
* [Kotlin](https://kotlinlang.org/)
* [AWS Lambda](https://aws.amazon.com/lambda/)
* [AWS Systems Manager](https://docs.aws.amazon.com/systems-manager/index.html)
* [REST-assured](https://rest-assured.io/)

## Versioning
This application uses [Semantic Versioning](https://www.conventionalcommits.org/en/v1.0.0/).

Given a version number MAJOR.MINOR.PATCH, increment the:

1. MAJOR version when you make incompatible API changes,
</br>```'BREAKING CHANGE:' a commit of the type feat introduces a breaking API change. A BREAKING CHANGE can be part of commits of any type.```
2. MINOR version when you add functionality in a backwards compatible manner, and
</br>```'feat:' a commit of the type feat introduces a new feature to the codebase.```
3. PATCH version when you make backwards compatible bug fixes.
</br>```'fix:' a commit of the type fix patches a bug in your codebase.```

To view this version we can run
```shell script
./gradlew currentVersion
```
Additional labels for pre-release and build metadata are available as extensions to the MAJOR.MINOR.PATCH format.

## Getting started
This section gives you instructions on building and setting up the project locally.

### Prerequisite
* [Java 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)

### Building the application
Launch the Gradle build on the checked out sources of this starter project.

Build the application using:
```shell script
./gradlew build
```
It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.


### Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

### Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

### Creating a native executable

You can create a native executable using: 
```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/quarkus-microservice-lambda-starter-1.0.0-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.

## Related Guides
***

- Kotlin ([guide](https://quarkus.io/guides/kotlin)): Write your services in Kotlin
- YAML Configuration ([guide](https://quarkus.io/guides/config#yaml)): Use YAML to configure your Quarkus application

## Provided Code

### YAML Config

Configure your application with YAML

[Related guide section...](https://quarkus.io/guides/config-reference#configuration-examples)

The Quarkus application configuration is located in `src/main/resources/application.yml`.

## Contact
***
Slack: [Team Platform](https://yaradigitalproduction.slack.com/archives/C01LF97E6TA)

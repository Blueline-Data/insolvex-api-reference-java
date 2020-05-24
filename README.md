# insolvex-api-reference-java

This repository contains a reference implementation in Java for the [API](https://api.insolvex.de/docs/index.html) of the [Insolvex insolvency database](https://insolvex.de).

It uses the published [Swagger API definition](https://api.insolvex.de/swagger.json) to automatically generate the client into the directory 
> insolvex-client/

Also a JUnit test is included which shows how to perform insolvency case search and how to create notification requests.

## Prerequisites

*  [Apache Maven](http://maven.apache.org)
* An active [Insolvex package](https://insolvex.de/#!/order) with API access
* An API token ("Mein Konto > Einstellungen > API Zugriff > Neuen API-Token erzeugen")

## Compilation of the client

To compile the Insolvex API client, just execute
```bash
mvn clean compile
```

This command fetches the Insolvex API definition from [https://api.insolvex.de/swagger.json](https://api.insolvex.de/swagger.json) and creates the API client.

## Execution of API calls

To perform API requests against the Insolvex API (**Warning**: Notification requests will be created!), just execute
```bash
export INSOLVEX_TOKEN=<YOUR_INSOLVEX_TOKEN>
mvn test
```


## About

This is the server-side of the Planet API project.

## Documentation
  
For the correct use of this API, you will need to look at the documentation located in the file [PLANET-API.yaml](./src/main/resources/swagger/planet/PLANET-API.yaml) with the [Swagger Editor](https://editor.swagger.io/)

## Requirements

The main dependencies of the project are:

| Technology | version |
| --- | --- |
| H2 Database | 1.4.200 |
| Maven | 4.0.0 |
| JDK | 11 |

## Generate sources  - API Interfaces and Models

Go to de project folder and run the following command:

```bash
mvn clean generate-resources
```

## Running project

Go to de project folder and run the following command:

```bash
mvn clean install spring-boot:run
```


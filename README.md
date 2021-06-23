# Exchange Rate Service
This project is built with the Spring Boot Framework.

## Getting Started
git clone "https://github.com/ahwinemman/exchange-rate.git"

To Run

### Option 1: Run Locally
#### Prerequisites
* JDK 11
* Maven

In the terminal window, cd into the root terminal of the project.
Build the project with the following command:
```shell script
mvn clean package
```
Then, to run the project's built jar file, run the following command:
```shell script
java -jar target/exchange-rate-0.0.1.jar --schedule.updateExchangeRate="<override_schedule>" 
```

### Option 2: Run in docker container 
#### Prerequisites
* Docker

In the terminal window, cd into the root terminal of the project.
Build the docker image with the following command:
```shell script
docker build -t <image-name> .
```
Then, to run the docker container detached, run the following command:
```shell script
docker run -d -e schedule.updateExchangeRate='<override_schedule>' -p 8080:8080 <image-name>
```

In both options the server will be listening on "http://localhost:8080", and the swagger documentation can be accessed on http://localhost:8080/swagger-ui.html

#### Recommendations
* Add endpoint to change exchangeUpdateRate schedule without server restart.
* Add DB/Cache to support features like a fallback exchange rate when provider service is down, rate-limiting etc.
* Add Authentication to secure endpoints.
# 🚀 My Spring Boot Application : receipt-processor 
A Spring Boot application built with Gradle to process receipts and calculate reward points.

(83% lines covered by unit tests)

## 📜 Description
This Spring Boot application, built using Gradle, calculates reward points for receipts based on predefined rules. It solves the challenge described in the receipt-processor-challenge. Here's a quick overview of how it works:

- The application accepts a JSON representing a receipt.
- Based on predefined rules, it calculates and returns the points for that receipt

## 📖 Swagger Doc
You can find the Swagger documentation for the API here: 
- [Link to Swagger](http://localhost:8080/swagger-ui/index.html#/)
- <a href = "http://localhost:8080/swagger-ui/index.html#/" target="_blank"> Click to open in a new tab </a>
- Screenshots for Swagger can be found at [SCREENSHOT](SCREENSHOT.md)
- Additional help can be found at [HELP ME](HelpMe.md)

```Link will be active once the application is running```

### 🏆 Rules for points:
These rules collectively define how many points should be awarded to a receipt.
- One point for every alphanumeric character in the retailer name.
- 50 points if the total is a round dollar amount with no cents.
- 25 points if the total is a multiple of 0.25.
- 5 points for every two items on the receipt.
- If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.
- 6 points if the day in the purchase date is odd.
- 10 points if the time of purchase is after 2:00pm and before 4:00pm.

## 📑 Table of Contents
- [Features](#-features)
- [Technologies](#-technologies)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Running the Application](#-running-the-application)
- [Docker](#-docker)
- [Usage](#-usage)
- [Testing](#-testing)

## 🛠️ Features
The application provides two main endpoints:

1. POST /receipts/process
This endpoint accepts a JSON payload representing the receipt and returns a unique identifier (id).

2. GET /receipts/{id}/points
This endpoint accepts the receipt ID returned from the POST method and returns the calculated points.

Refer to the [Usage](#-usage) and [Testing](#-testing) sections for further details on how to interact with these endpoints.


## 🧰 Technologies
This project is built with:
- <a href="https://spring.io/projects/spring-boot" target="_blank"> Spring Boot </a> - Framework for Java-based applications
- <a href="https://gradle.org/" target="_blank"> Gradle </a> - Build automation tool
- <a href="https://www.h2database.com/" target="_blank"> H2 Database </a> - In-memory database (or mention your DB here)
- <a href="https://hibernate.org/" target="_blank"> JPA/Hibernate </a> - ORM for database interaction

## ⚙️ Installation

### Prerequisites 
- JDK 17 or higher <a href="https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html" target="_blank"> Download JDK </a>
- Gradle 8.x (or you can use the Gradle Wrapper) <a href="https://gradle.org/install/" target="_blank"> Install Gradle </a>

### Steps
1. Clone this repository:
    ```bash
    git clone https://github.com/ab13roy/receipt-processor.git
    cd your-repo
    ```

2. Build the project with Gradle:
    ```bash
    ./gradlew build
    ```

3. Run the application:
    ```bash
    ./gradlew bootRun
    ```

### The application will start running on `http://localhost:8080`.
### More details on running the application can be found at [HELP](HELP.md)

## 🔧 Configuration

You can configure the application by editing the ```application.properties``` file located at ```src/main/resources/application.properties```.

Example configuration for a database:
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
```

## 🚀 Running the application

- Once the command ```./gradlew build``` is executed a jar file will be created. </br>
- This jar file can be found at path ```~/build/libs/* ``` </br>
- Running the build commands will replace previously existing jar files, unless the build version specified in the ```build.gradle``` file at line ```#8 version = '1.0.1-SNAPSHOT'``` is updated. </br>

Once the project is built, you can run the application in different ways:

### Using Java Cli
- After building the project, navigate to the build/libs folder:
```shell
cd /build/libs
```
- Run the application using:
```shell
java -jar receipt-processor-1.0.1-SNAPSHOT.jar
```

### Using Docker
If you prefer Docker, you can build and run the Docker image as follows:
- Build the Docker image:
```shell
docker build -t receipt-processor .
```
- Run the Docker container
```shell
docker run -p 8080:8080 receipt-processor
```

## 🐳 Docker
This section will guide you through Dockerizing the application.
- Please refer official docker documentation at <a href="https://docs.docker.com/" target="_blank"> Docker </a> for any guides.
- First, ensure that you have Docker installed. <a href="https://docs.docker.com/get-started/get-docker/" target="_blank"> Docker Desktop</a>.
- The project already includes a Dockerfile for building the Docker image.

### Steps to Create the Docker Image
- Navigate to the project directory and build the image:
```shell
docker build -t receipt-processor .
```
- Verify that the image has been created:
```shell
docker images
```
- Run the container with:
```shell
docker run -p 8080:8080 receipt-processor
```
### Additional help can be found at [HELP ME](HelpMe.md)

## 📝 Usage

#### Sample JSON payload
Payload 1
```json
{
  "retailer": "Target",
  "purchaseDate": "2022-01-01",
  "purchaseTime": "13:01",
  "items": [
    {
      "shortDescription": "Mountain Dew 12PK",
      "price": "6.49"
    },{
      "shortDescription": "Emils Cheese Pizza",
      "price": "12.25"
    },{
      "shortDescription": "Knorr Creamy Chicken",
      "price": "1.26"
    },{
      "shortDescription": "Doritos Nacho Cheese",
      "price": "3.35"
    },{
      "shortDescription": "   Klarbrunn 12-PK 12 FL OZ  ",
      "price": "12.00"
    }
  ],
  "total": "35.35"
}
```
Payload 2
```json
{
  "retailer": "M&M Corner Market",
  "purchaseDate": "2022-03-20",
  "purchaseTime": "14:33",
  "items": [
    {
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    }
  ],
  "total": "9.00"
}
```
### Example API Calls:
#### POST ```/receipts/process```
- Request
```bash
curl --location 'localhost:8080/receipts/process' \
--header 'Content-Type: application/json' \
--data '{"retailer": "Target", "purchaseDate": "2022-01-01", "purchaseTime": "13:01", "items": [{"shortDescription": "Mountain Dew 12PK", "price": "6.49"}, {"shortDescription": "Emils Cheese Pizza", "price": "12.25"}, {"shortDescription": "Knorr Creamy Chicken", "price": "1.26"}], "total": "35.35"}'
```
- Response
```json
{ "id": "8c6a5fa3-363b-456b-89ec-948a5912ff97" }
```

#### GET```/receipts/{id}/points```
Using the response from Payload 1
- Request
```bash
curl --location 'localhost:8080/receipts/8c6a5fa3-363b-456b-89ec-948a5912ff97/points'
```
- Response
```json
{ "points": 28.0 }
```
Breakdown:
```
- 6 points - retailer name has 6 characters
- 10 points - 5 items (2 pairs @ 5 points each)
- 3 Points - "Emils Cheese Pizza" is 18 characters (a multiple of 3)
- item price of 12.25 * 0.2 = 2.45, rounded up is 3 points
- 3 Points - "Klarbrunn 12-PK 12 FL OZ" is 24 characters (a multiple of 3)
- item price of 12.00 * 0.2 = 2.4, rounded up is 3 points
- 6 points - purchase day is odd
+ ---------
= 28 points
```

## 🧪 Testing
- Invalid Payload: If the payload is invalid, the server will return a <b><u>"400 Bad Request</u></b> with the message <b><u>"Unable to process request."</u></b>
- Invalid ID: If an invalid receipt ID is provided for the GET request, the server will return a <b><u>"404 Not Found"</u></b> with the message <b><u>"Unable to find receipt."</u></b>
- Validations are made to <u>cost, date, and time</u> fields. Failure to validate these fields will also return a <b><u>"400 Bad Request"</u></b>.

## Screenshots
- Find them here at [SCREENSHOT](SCREENSHOT.md)


## 🧗‍♂️ Having trouble?
- This document will guide you through
- Once you have all the Prerequisites to run the project follow the following steps to run the applicaiton.

## 📂 Contents
- [Running Using IntelliJ](#-running-using-intellij)
- [Running Using Docker](#-running-using-docker)
- [Navigating H2 Database](#-navigating-h2-database)
- [Accessing Swagger Doc](#-accessing-swagger-doc)

## 🏃 Running Using IntelliJ
1. Download IntelliJ from <a href="https://www.jetbrains.com/idea/download" target="_blank">here</a>.
2. Import the project
    1. Clone this repository: </br>
       ```git clone https://github.com/ab13roy/receipt-processor.git```
    2. Open IntelliJ, click File on the top menu.
    3. Select Open, navigate to the directory where the repo is cloned and click ok.
    4. Accept the 'Trust Project' pop up.
3. Now IntelliJ should automatically download Gradle and JDK 17.
4. Run the following gradle commands
    1. 🐘 symbol on the most menu at the right edge of the screen will showcase all gradle commands
    2. On windows hit 'ctrl', or on mac hit '^ control' twice.
    3. A run anything window will pop up
    4. here run </br>
       ``` gradle build ```
5. Now the jar file will be created.
    1. you can find the jar file at ```~/build/libs/* ```
6. Open the class with the [main function](src/main/java/com/fetch_rewards/receipt_processor/ReceiptProcessorApplication.java)
7. A  green ▶ will pop on the line of the main class and the main function.
8. Click any one of them to run the project

## 🐳 Running Using Docker
- To run the project using Docker. You need only one thing. That is Docker Desktop on your system
- Download Docker Desktop  from <a href="https://docs.docker.com/get-started/get-docker/" target="_blank">here</a>.
- You do not need IntelliJ or JDK or Gradle to run, if you are using the docker image.


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
#### *With this the project should be up and running at port 'http://localhost:8080'*

## 🛢️ Navigating H2 Database
- This application makes use of in memory H2 Database.
- Once the application is running, the database console can be accessed through the browser.
- H2 Database console 'http://localhost:8080/h2-console'
- Username and password for login can be found at

### Accessing when running the application using IntelliJ
- When running using IntelliJ. You can log in directly using the username and password from the [properties file](/src/main/resources/application.properties).
### Accessing when running the application using Docker
- When running using docker image. Update the JDBC url at the login screen. JDBC url can be found at [properties file](/src/main/resources/application.properties).We need to do this because the H2 is currently running in a docker container and we are remotely trying to access it.

## 📚 Accessing Swagger Doc
- Once the application is running. Regardless the way you chose to the run the application. Swagger Doc can only be accessed at 'http://localhost:8080/swagger-ui/index.html#/'
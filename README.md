# payment-sample

This project contains Spring security authentication using login API and a Authorization bearer token. 
A simple bill inquiry service is implemented with assumption of existance two providers. Changing provider type is configurable in application.yml.
The project is docerized using docker file and docker compose.

# Run
1. Build project by maven:
   mvn clean package
   Note that one integration test is run at the end. Database starts by mysql container and provider urls are mocked by wiremock.
2. Copy openjdk17, version 17.0.5 with exact name as jdk-17.0.5_linux-x64_bin.tar.gz in jars diretory of projects payment-service and mock-server:
   Path 1: payment-system/mock-server/jars
   Path 2: payment-system/payment-service/jars
3. Go to the root of the project in an open terminal and run command:
   Docker compose up

 The project will start successfully. 
 Swagger URL: http://localhost:8080/swagger-ui/index.html

 To login and test use below data:
 usaname: admin
 password: 123456

 At the start of project it is stored in DB using Liquibase

<!-- ABOUT THE PROJECT -->
## About The Project
### Customer-Phone-Service 
An API made to serve customers and allows a functionality of phone number activation

### Built With
* Java11
* Springboot 2.7
* Actuator
* Swagger Open API Spec
* Junit
* JPA
* H2 database
* BDDMockito
* Gradle

<!-- GETTING STARTED -->
## Getting Started
Checkout on local using git. 
### Prerequisites
Install following on your system if missing.
* java-11
* gradle-7.6
* Intellij or your favourite ide

Make sure your system has Java and Gradle on the path.
#### Installation
1. Clone the repo
 ```sh
   git clone https://github.com/Sandeep-Mandori/customer-phone-service.git
  ```
2. Build the project using gradle command
  ```sh
  ./gradlew clean build
  ```
  Note: gradlew - wrapper is within the project so in case you don't want to use it, you would need following
command
  ```sh
  ./gradle clean build
  ```
Make sure you see the success on the console like following

3. Set the spring.profiles.active environment variable in the console or in ide from where you plan to run the
application.
  - on windows
  ```sh
  set SPRING_PROFILES_ACTIVE=local
  ```
  - on linux/unix
  ```sh
  export SPRING_PROFILES_ACTIVE=local
  ```
Note: in case you don't have this environment variable set, you will receive database related errors.
This project is currently using in memory h2 database for development purpose.
4. Run the project by using following command
  ```sh
  gradlew bootRun
  ```
### Key points and usage information
* Application starts on port 9000 with root context '/phone-service'
* Swagger can be accessed on following url
 - #### Swagger ui url
    ```sh
    http://localhost:9000/phone-service/swagger-ui/index.html
    ```
 - #### Swagger API spec documentation url
    ```sh
    http://localhost:9000/phone-service/v3/api-docs
    ```
* H2 database console can be accessed via web browser to validate the current data and changes around the same
 - #### H2 console URL
    ```sh    
    http://localhost:9000/phone-service/console/h2
    ```
* Application currently uses the permit all strategy for security aspect and further improvements can be done 
based on requirement
* Unit test cases are still on the go, basic level of testing is embedded
* H2 is used for the temporary testing and intend to be replaced with more production grade database
* Application uses pagination for some of the endpoints, infect have paginated and non-paginated version of 
the endpoints
* Actuator has been implemented on the following endpoint
 - ### Actuator Endpoint
   ```sh
   http://localhost:9000/phone-service/actuator
   ```
 
# RESTful Consignment Note Number Generator
## Tech Stack:
* Java 11
* SpringBoot
* Gradle
* Lombok
* Spring Data JPA
* PostgreSQL
* H2 in-memory DB
* Docker
* Docker compose
* Mockito
* MockMvc
* Flyway

## Topics
1. [How to run this application](#How-to-run-this-application)
2. [How to access the spring boot restful application](#How-to-access-the-spring-boot-restful-application)
3. [Advantages of this application](#Advantages-of-this-application)

### This is the springBoot based RESTful API. It supports the below functions:

* Application supports H2 in-mem DB and PostgreSQL DB, by default PostgreSQL DB is being used. If you want to use H2 in-mem DB, application-h2.yml shall be used
* Generate Consignment Note Number according to input data

## How to run this application

* Navigate the root folder **/consignment** under the command line
* Run the command to build the whole project: **gradle clean build**
* Either run the command to start the application with H2 DB: **java -jar -Dspring.profiles.active=h2 ./build/libs/consignment-0.0.1-SNAPSHOT.jar**
* Or run **docker-compose up**, then the application and PostgreSQL DB are both up and running


## How to access the spring boot restful application
* Please use **Postman** to test the API
* You can access via the URL below with **POST** Http Method:
`http://localhost:8080/consignment/connot-number`
* Send a JSON Body along with the request to the URL. Here is an example of data sent.
```
{
  "carrierName":"FreightmateCourierCo",
  "accountNumber":"123ABC",
  "digits":10,
  "lastUsedIndex":19604,
  "rangeStart":19000,
  "rangeEnd":20000
  }
```
* Alternatively, you can find the postman collection file in postman directory and modify the data inside JSON body as you want

## Advantages of this application
* Hibernate builds the entity layer
* Flyway prepares the initial data
* Spring Data JPA builds the repository layer & H2 in-mem database used as Unit Test to test this layer
* Mockito and MockMvc unit test service and controller layers
* Defined customised exceptions for better error handling and all the exceptions can be centrally handled in one place (ControllerExceptionHandler.java)
* Lombok automatically generates getter,setter, constructor, hashcode, log etc.
* Docker compose has been used in local to easily set up the local environment
* Dockerfile utilize multiple stages and put application files into docker instead of using fat jar
* High test coverage
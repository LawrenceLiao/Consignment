# RESTful Consignment Note Number Generator
## Tech Stack:
* Java 11
* SpringBoot
* Lombok
* Spring Data JPA
* H2 in-memory DB
* Mockito
* MockMvc
* Flyway

## Topics
1. [How to run this application](#How-to-run-this-application)
2. [How to access the spring boot restful application](#How-to-access-the-spring-boot-restful-application)
3. [PostgreSQL version based Wholesale Application](#PostgreSQL-version-based-Wholesale-Application)
4. [Advantages of this application](#Advantages-of-this-application)

### This is the springBoot based RESTful API. It supports the below functions:

* Application support H2 in-mem DB and Postgres DB, by default postgres DB is being used. If you want to use H2 in-mem DB, application-h2.properties shall be used.
* List all accounts
* List all transactions by account id


## How to run this application

* Navigate the the root folder /consignment under the command line
* Run the command to build the whole project: **gradle clean build**
* Run the command to start the application: **java -jar ./build/libs/consignment-0.0.1-SNAPSHOT.jar**


## How to access the spring boot restful application
* Please use postman to test the API
* You can access via the URL below:
`http://localhost:8080/consignment/connot-number`
* Send a JSON body along with the request to the URL. Here is an example of data sent.
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
* Alternatively, you can find the postman collection file in postman folder and modify the data inside JSON body as you want

## Advantages of this application
* Hibernate builds the entity layer
* Flyway prepares the initial data
* Spring Data JPA builds the repository layer
* Mockito and MockMvc unit test service and controller layers
* Defined customised exceptions for better error handling and all the exceptions can be centrally handled in one place (ControllerExceptionHandler.java)
* Lombok automatically generates getter,setter, constructor, hashcode, log etc.
* High test coverage
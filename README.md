### Application Functionality
This sample backend application will expose two APIs
````
1) http://{host}:{port}/user/accounts
2) http://{host}:{port}/user/accounts/{account-id}/transactions?pageNumber={pageNumber}&pageSize={pageSize}
````
#### Swagger Details
- Swagger URL : http://localhost:8080/swagger-ui/index.html
### Run Application Locally

#### Required version
- Java 11 - To run this application you need have Java 11 setup locally

#### Useful gradle commands

- Run application
````
./gradlew bootRun
````
- Run Test
````
./gradlew test
````
### Assumptions

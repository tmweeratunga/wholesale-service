### Application Functionality
This sample backend application will expose two APIs
````
1) http://{host}:{port}/user/accounts
2) http://{host}:{port}/user/accounts/{account-id}/transactions?pageNumber={pageNumber}&pageSize={pageSize}
````
#### Swagger Details
- Swagger URL : 
### Run Application Locally

####Required version
- Java 17 - To run this application you need have Java 17 setup locally

####Useful gradle commands

- Run application
````
./gradlew bootRun
````
- Run Test
````
./gradlew test
````
### Assumptions

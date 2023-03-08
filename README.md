### Application Functionality
This sample backend application will expose two GET APIs
````
1) http://{host}:{port}/user/accounts
2) http://{host}:{port}/user/accounts/{account-id}/transactions?pageNumber={pageNumber}&pageSize={pageSize}
````
#### API Details
- Swagger URL : http://localhost:8080/swagger-ui/index.html
- Authentication token : Login outside of scope and authentication and authorization not implemented. 
- For both APIs, need to pass valid user id in authentication token as Bearer token. Token directly use as a user id
````
Eg : authorization: Bearer 123456
````

#### Curl commands
- get accounts
````
curl -X 'GET' \
  'http://localhost:8080/user/accounts' \
  -H 'accept: application/json' \
  -H 'authorization: Bearer 123456'
````

- get transactions
````
curl -X 'GET' \
  'http://localhost:8080/user/accounts/0001/transactions?pageNumber=0&pageSize=2' \
  -H 'accept: application/json' \
  -H 'authorization: Bearer 123456'
````

#### Database Details
- H2 embedded database use in this application
- In dev and test application profiles H2 console will be enabled : http://localhost:8080/h2/login.jsp
````
database : {env_name}_db
username : {env_name}_user
password : {env_name}_password
env_name : dev / test / prod
````
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

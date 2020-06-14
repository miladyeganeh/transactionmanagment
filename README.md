# Account Management Service 
Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

## Requirements

For building and running the application you need:

- [JDK 1.11](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [gradle-6.3](hhttps://gradle.org/)

## Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.my.accountmanager.AccountManager` class from your IDE.

Alternatively you can use the Docker. For deploying write below command line in application directory

```shell
docker-compose up --build --force-recreate --no-deps [-d] [<service_name>..]
```

This will create:

* Api for creating payment between two account
* Api for Accounts
* Api for transaction document 
* Report for transaction report

If you want to access the api you can visit documentation on http://localhost:8080/swagger-ui.html#/

http://localhost:8080/swagger-ui.html#/

## Transaction request sample

{ 
	"trxDate": "2020-06-13T13:13:21.091+00:00",
	"amount": 500,
	"currencyCode": "EUR",
	"terminalId": "112",
	"transactionType": 1,
	"cardPAN": "1234.1234.1234.1234",
	"hashedPassword": "@#$%HRGB#$@#$@#$@$FGHDSFSDF",
	"cvv": "800",
	"expireDateMonth": "10",
	"expireDateYear": "23",
	"sourceAccountNumber": "400",
	"destinationAccountNumber":"500"
}

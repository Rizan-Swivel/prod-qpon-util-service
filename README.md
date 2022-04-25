# Util Service
This micro-service provides util services like sending sms and emails. 

## Requirements
* Spring boot 2.2.1
* Open JDK 11
* Redis 5.0.7
* Maven 3.6 (only to run maven commands)


## Configuring AWS Services 
#### SNS - Simple Notification Service 
#### SES - Simple Email Service

- For both services to work you need to add your aws credentials to verify your aws account. Follow the below documentation

  [https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-credentials.html]

- Define the sender email address and subject in below properties located in <otp-service>/src/main/resources/application.properties file

* util.email.sender=
* util.email.subject=

## Configuring the micro-service

* Add the password to redis server in <otp-service>/src/main/resources/application.yml
* Fill out the relevant configurations in application.yml and bootstrap.yml in src/main/resources
  before building the application

## Dependencies

All dependencies are available in pom.xml.

## Build
```
mvn clean compile package
```

## Run

```
mvn spring-boot:run
```
or 
```
java -jar target/util-service-1.0.1-SNAPSHOT.jar
```

## Test
```
mvn test
```

### Reference Documentation
For further reference, please consider the following sections:

* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/maven-plugin/)


## License

Copyright (c) Swivel - 
This source code is licensed under the  license. 

Test

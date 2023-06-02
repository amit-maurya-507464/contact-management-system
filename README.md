# Contact Management System
Spring Boot Project for contacts management with authentication and authorisation using Spring Security and JWT.

## Runing and Testing
**IDE:** 
Open project in any IDE and run as a spring boot Project. <br>
**Command line:** 
Open terminal and loacte to pom.xml file directory and type command - 
`mvn dpring-boot:run`

Use the following url to test the Application:
http://localhost:8080/swagger-ui/index.html


## Authentication and Authorization

It has authentication and authorization implemented by spring security and JWT (JSON Web Token).
1. Jwt Token based authentication on APIs
2. Role based authorization on APIs
3. Privileges based authentication on APIs

![auth.png](assets%2Fimages%2Fauth.png)

## Documentation
This project uses springdoc-openapi for documentation.
springdoc-openapi java library helps to automate the generation of API documentation using spring boot projects.

![swagger.png](assets%2Fimages%2Fswagger.png)

**This library supports:**
1. OpenAPI 3
2. Spring-boot (v1, v2 and v3)
3. JSR-303, specifically for @NotNull, @Min, @Max, and @Size.
4. Swagger-ui
5. OAuth 2
6. GraalVM native images

## Rest APIs
It has Rest API for Contacts management which includes creating, editing, searching, deliting and getting data.
It has signup and login APIs.

## Database
It has in memory H2 database configured.
During the application startup it creates following data in database:
1. Default Privileges
2. Default Roles
3. Super Admin user

![h2-db.png](assets%2Fimages%2Fh2-db.png)


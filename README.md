# User progress tracking system 

Please follow below instructions to run this project:


You should have JDK-8 and MySql-8 installed in your PC to run this application.


Technology stack:
1. Java - 8
2. Spring Boot 
3. Spring Data JPA
4. Maven
5. Mysql
6. Flyway
7. ModelMapper
8. Swagger
9. Devtools
10. lombok

Run Commands:
1. mvn clean
2. mvn install
3. mvn spring-boot:run

Generate and run jar file:
1. mvn clean install.
2. cd target
3. java -jar user-progress.jar
```
###Browse swagger:
1. After running code please check API documentation swagger ui 
2. Swagger UI: http://localhost:8080/swagger-ui.html
```

#### For Database Migrations with Flyway, add maven dependency in pom.xml

```
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
```
Create a folder db.migration under resources folder and then
create SQL scripts under this folder. 
Flyway adheres to the following naming convention for migration scripts:
```
<Prefix><Version>__<Description>.sql
For example: V1__add_users.sql
```
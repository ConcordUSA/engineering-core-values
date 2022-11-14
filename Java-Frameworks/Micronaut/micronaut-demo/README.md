## Introduction
[Micronaut](https://micronaut.io) is a java framework with main focus on building
cloud native applications. By cloud native, I mean the framework has native support for most of the 
cloud native features such as [Service Discovery](https://micronaut-projects.github.io/micronaut-discovery-client/latest/guide/), 
[Distributed Tracing](https://micronaut-projects.github.io/micronaut-tracing/latest/guide/), etc. <br>

We will be creating a CRUD application using the following:

- [Postgres](https://www.postgresql.org/) database as persistence layer
- [Hibernate Reactive JPA](https://hibernate.org/reactive/) as our ORM
- [Flyway](https://flywaydb.org/) for managing database migrations
- [Project Reactor](https://projectreactor.io/) and [Lombok](https://projectlombok.org/)
- [Docker](https://docker.com) for orchestrating integrated environment
- JDK 17

**If you are using Jetbrains IntelliJ as IDE, make sure enable annotation processing as described
below in the screenshot**<br>

![Enable Annotation Processing in IntelliJ IDE](/src/main/resources/Enable_Annotation_Processing.png "Enable Annotation
Processing")

## Dependencies
Let's start by investigating the contents of `build.gradle` file. 
We are going to add the following in the `dependencies` section to import the
libraries we are going to use for the application <br>
```
annotationProcessor("org.projectlombok:projectlombok") 
annotationProcessor("io.micronaut.data:micronaut-data-processor")
implementation("org.projectlombok:lombok")
implementation("io.micronaut.data:micronaut-data-hibernate-reactive")
implementation("io.micronaut.reactor:micronaut-reactor")
implementation("io.micronaut.reactor:micronaut-reactor-http-client")
implementation("io.micronaut.sql:micronaut-vertx-pg-client")
implementation("org.postgresql:postgresql")
implementation("com.ongres.scram:client:2.1")
implementation("org.flywaydb:flyway-core:9.7.0")
```

Please ensure that `lombok` is listed first in the `annotationProcessor` order list. Since micronaut depends heavily 
on Annotation Processing , this step is a requisite for `lombok` to play nicely with the framework.

## Database 
We are going to create script files to get the database up and running. We will be using `Docker` to stand up our
database. Create a file `database.yml` with the following contents: <br>
```yaml
version: "3"
services:
  db:
    image: postgres:alpine
    environment:
      POSTGRES_USER: concord
      POSTGRES_PASSWORD: Concord123456!
    ports:
      - "5432:5432"
```
We will be using the lightweight `alpine distro` based docker image to keep the image size small. Let's also create a
script file to start up the database. Create a file `start-db.sh` with the following contents: <br>
```shell
#!/usr/bin/env bash
docker-compose -f database.yml up -d
```
Executing the above script file should start our database in the background. To confirm that the database is up,
execute the following command in the terminal: <br>
`docker ps -a` <br>
The above command should list all the containers. You should see container with the name
`micronaut-demo-db-1` up and running. 

## Application Config
Configuration in Micronaut takes inspiration both from `Spring Boot` and `Grails`. Configuration can by default be 
provided by Java properties, YAML, JSON or Groovy files. The convention is to search for file named `application.yml`, 
`application.json`, `application.properties` or `application.groovy`. <br>
Under `src/main/resources` folder, open `application.yml`. It should have the following content:<br>
```yaml
micronaut:
  application:
    name: micronautDemo
netty:
  default:
    allocator:
      max-order: 3
```
Let's add the following content to the above: <br>
```yaml
datasources:
  default:
    url: ${JDBC_URL:`jdbc:postgresql://localhost:5432/postgres`}
    username: ${JDBC_USER:concord}
    password: ${JDBC_PASSWORD:Concord123456!}
jpa:
  default:
    reactive: true
    properties:
      hibernate:
        hbm2ddl:
          auto: none
        connection:
          url: ${datasources.default.url}
          username: ${datasources.default.username}
          password: ${datasources.default.password}
```
`datasources` section is to specify the db connection details, which in turn are used in the `jpa` configuration section.
Notice how we are also setting `reactive` property of `jpa` to be `true`. We are setting `hbm2ddl` `auto` setting as `none` 
since we will be using `Flyway` to handle our database migrations.

## Domain
At this point we have our persistence layer up and running. Let's focus on building out our microservice. Our service 
has very rudimentary business logic. It exposes an endpoint `/users` which in turn supports the CRUD (CREATE, READ, 
UPDATE and DELETE) operations. Create a file `AppUser.java` with the following contents: <br>
```java
@Entity
@Table(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_seq")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Nullable
    private String email;
}
```
Let's dissect the above class and the various annotations used. `@Entity` and `@Table` is used by hibernate to uniquely
identify the entity in the database. Notice how we are passing additional options to `@Table` annotation to specify the
name of the table in the database. `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor` and `@Builder` are
`Lombok` annotations used to generate the boilerplate java code. <br>

The `id` field is annotated with `@Id` and `@GeneratedValue` . Those are used to specify the `primary key` as well as 
define the strategy for generating that `ID` . The `firstName` field is annotated with `@Column` with additional options 
passed to uniquely identify the column. Further on the `email` field, we are using `@Nullable` annotation provided by 
Micronaut to hint that the value can be null while serializing/de-serializing the data.

## Repository
Now that we have our domain modeled out, it's time to build out the repository layer to perform data operations on the 
entity. Create a file `AppUserRespository.java` with the following contents: <br>
```java
@Repository
public interface AppUserRepository extends ReactorCrudRepository<AppUser, Long> {
}
```
`Micronaut-Data` during compile time implements the above repository to provide CRUD operations out of the box. Refer to
[this](https://micronaut-projects.github.io/micronaut-data/latest/guide/) for more documentation on the topic. <br>

We are also going to create DTO to be used for persisting and updating data in the database. Create a file
`AppUserSaveRequest.java` with the following content: <br>
```java
@Introspected
public record AppUserSaveRequest(@NotNull String firstName,
                                 @Nullable String lastName,
                                 @Nullable String email) {
}
```
Create a file `AppUserUpdateRequest.java` with the following content: <br>
```java
@Introspected
public record AppUserUpdateRequest(@NotNull Long id,
                                   @Nullable String firstName,
                                   @Nullable String lastName,
                                   @Nullable String email) {
}
```
`@Introspected` annotation is used by the framework for `BeanIntrospection`. It allows the framework to avoid using
`reflection`. More information on the annotation can be found [here.](https://www.cs.auckland.ac.nz/references/java/java1.5/tutorial/javabeans/introspection/index.html) <br>

Notice how we are using `Java Records` to encapsulate our DTO. With records, we don’t need to specify any getter or 
setter, which makes the code clean and robust.

## Controller
With our repository all wired up, let's create the `Controller` to expose the `/users` endpoint. Create a file `AppUserController.java`
with the following content: <br>
```java
@Controller("/users")
@AllArgsConstructor
@Slf4j
public class AppUserController {

    private final AppUserRepository appUserRepository;

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Publisher<AppUser> getById(@PathVariable(name = "id") Long id) {
        log.info("Retrieving user with ID: " + id);
        return appUserRepository.findById(id);
    }
    
    @Post
    @Status(HttpStatus.CREATED)
    Publisher<HttpResponse<AppUser>> save(@Valid @Body AppUserSaveRequest command) {
        val savedEntity = AppUser.builder()
                .firstName(command.firstName())
                .lastName(command.lastName())
                .email(command.email())
                .build();
        return appUserRepository.save(savedEntity)
                .map(appUser -> HttpResponse
                        .<AppUser>noContent()
                        .header(HttpHeaders.LOCATION, URI.create("/users/" + appUser.getId()).getPath()));
    }

    @Put
    @Status(HttpStatus.NO_CONTENT)
    Publisher<HttpResponse<AppUser>> update(@Valid @Body AppUserUpdateRequest command) {
        val updatedEntity = AppUser.builder()
                .id(command.id())
                .firstName(command.firstName())
                .lastName(command.lastName())
                .email(command.email())
                .build();
        return appUserRepository.update(updatedEntity)
                .map(appUser -> HttpResponse
                        .<AppUser>noContent()
                        .header(HttpHeaders.LOCATION, URI.create("/users/" + appUser.getId()).getPath()));
    }

    @Delete("/{id}")
    Publisher<HttpResponse<?>> deleteById(@PathVariable Long id) {
        return appUserRepository.deleteById(id)
                .map(deleteId -> HttpResponse.noContent());
    }
}
```
`@Controller` annotation is used to mark the controller class. It additionally takes an optional value (`/users` in our case) 
to specify the endpoint. We inject the `AppUserRepository` via constructor injection. The methods in this class corresponds
to the CRUD operations on the AppUser entity. Notice how we can simply annotate the method with `@Status` to specify the
expected status of the corresponding CRUD operation. We are also validating the incoming request by using `@Valid` & `@Body`
annotations for the method parameters.

## Database migrations
One of the last thing to wire up the application is support for database migrations. We will be using `Flyway` to accomplish
that. Although Micronaut has built in support for `Flyway`, we won't be using that since `Flyway` currently does not work 
well with `Hibernate Reactive`. To work around it, create a file `Dataloader.java` with the following content: <br>
```java
@Singleton
@Requires(notEnv = TEST)
public class Dataloader {

    private static final String SCHEMA_LOCATION = "db_migrations";

    private final String url;
    private final String username;
    private final String password;

    Dataloader(@Property(name = "datasources.default.url") String url,
               @Property(name = "datasources.default.username") String username,
               @Property(name = "datasources.default.password") String password) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    @EventListener
    @Async
    void initiateMigration(final StartupEvent startupEvent) {
        Flyway.configure()
                .locations(Location.FILESYSTEM_PREFIX + SCHEMA_LOCATION)
                .dataSource(url, username, password)
                .load()
                .migrate();
    }

}
```
`@Singleton` annotation is used to initialize this bean on application startup. `@Requires` annotation is used to instruct
the framework to only load this bean in a non-test environment. Let's also create a folder in the root directory named
`db_migrations`. As mentioned in the above file, we will be using this directory to house all of our `SQL` files for 
database migrations. Notice how we are using `@Properties` annotation that allows us to fill in the values for the 
`url, username and password` from `application.yml` file. <br>

We want `Flyway` to run the migrations on application startup. To achieve that, we are annotating the method we want to
execute at startup with `@EventListener` and pass in the `StartupEvent` as the method parameter. We are also annotating
the method with `Async`. This helps in the execution of the method on a separate thread in case the application startup 
is taking a lot of time. 


# Cognizant-Digital-Nurture-5.0

A repository containing all hands-on exercises and assignments completed as part of the **Cognizant Digital Nurture 5.0** program.

---

## 📁 Repository Structure

```
Cognizant-Digital-Nurture-5.0/
├── Week1/
│   ├── Algorithms_Data_Structures/
│   ├── Design-Principles-and-Design-Patterns/
│   ├── PLSQL_Exercises/
│   └── JUnit-Mockito-Logging-exercises/
│       ├── JUnit-Advanced-exercises/
│       ├── JUnit-exercises/
│       ├── junit-spring-test-exercises/
│       ├── mockito-advanced-exercises/
│       ├── Mockito-exercises/
│       ├── mockito-mock-dependencies-exercises/
│       └── slf4j-logging-exercises/
├── Week2/
│   ├── Spring-Core-and-Maven/
│   └── Spring-JPA-and-Hibernate/
├── Week3/
│   └── Spring-REST/
│       ├── 1. Spring REST/
│       ├── 2. Spring REST/
│       ├── 3. Spring REST/
│       ├── 4. Spring REST/
│       └── jwt-handson/
└── Week4/
    ├── microservices-api-gateway/
    ├── microservices-exercises/
    ├── microservices/
    └── microservices2/
```

---

## 📚 Modules

### Week 1

#### 1. Algorithms & Data Structures
Covers fundamental algorithms and data structure implementations.

#### 2. Design Principles & Design Patterns
Covers SOLID principles and common design patterns like Creational, Structural and Behavioral patterns.

#### 3. PL/SQL Exercises
Covers Oracle PL/SQL programming including stored procedures, functions, cursors, triggers and exception handling.

#### 4. JUnit, Mockito & SLF4J Logging Exercises
Covers unit testing, mocking, Spring Test slices, and logging fundamentals.

- **JUnit-Advanced-exercises** / **JUnit-exercises** — Core and advanced JUnit 5 testing exercises
- **junit-spring-test-exercises** — Spring testing exercises: `@WebMvcTest`, `@DataJpaTest`, `@SpringBootTest` integration tests, and `@ParameterizedTest`
- **mockito-advanced-exercises** / **Mockito-exercises** — Mocking repositories, REST clients, file I/O, network interactions, and multiple return values with Mockito
- **mockito-mock-dependencies-exercises** — Mocking service/repository dependencies across controller tests, service tests, and Spring Boot integration tests
- **slf4j-logging-exercises** — Logging error/warning levels, parameterized logging, and configuring multiple Logback appenders (console + file)

---

### Week 2

#### 1. Spring Core & Maven
Covers Spring Core concepts including Dependency Injection, Bean configuration, Spring MVC and Maven project setup.

#### 2. Spring JPA & Hibernate
Covers Object-Relational Mapping using Hibernate and Spring Data JPA.

**Hands-on 1 — Spring Data JPA Basics**
- Setting up Spring Boot with Spring Data JPA
- Configuring datasource and Hibernate properties
- Creating `Country` entity and `CountryRepository`
- Implementing `CountryService` with `getAllCountries()`

**Hands-on 2 — Query Methods & ORM Relationships**
- Spring Data JPA Query Methods (containing, starting with, sorting)
- Stock data queries (between dates, greater than, top N)
- `@ManyToOne`, `@OneToMany`, `@ManyToMany` mappings
- `FetchType.EAGER` vs `FetchType.LAZY`
- `@JoinColumn`, `@JoinTable`, `mappedBy`

**Hands-on 3 — HQL, Native Queries & Criteria API**
- HQL vs JPQL introduction
- `@Query` annotation with HQL and `fetch` keyword
- Aggregate functions (`AVG`, `SUM`, `COUNT`)
- Native Queries with `nativeQuery = true`
- Criteria Query for dynamic filtering

**SpringDataJPA Hibernate Exercises — Employee Management System**
- **Exercise 1** — Project setup with H2 Database, Spring Data JPA, Lombok
- **Exercise 2** — JPA Entities (`Employee`, `Department`) with `@OneToMany` / `@ManyToOne`
- **Exercise 3** — Repositories extending `JpaRepository` with derived query methods
- **Exercise 4** — REST CRUD endpoints using `@RestController`
- **Exercise 5** — `@Query`, `@NamedQuery`, `@Param` annotations
- **Exercise 6** — Pagination (`Page`, `Pageable`) and Sorting (`Sort`)
- **Exercise 7** — Entity Auditing (`@CreatedBy`, `@LastModifiedBy`, `@CreatedDate`, `@LastModifiedDate`)
- **Exercise 8** — Projections (interface-based, class-based DTO, `@Value` SpEL)
- **Exercise 9** — Multiple DataSource configuration
- **Exercise 10** — Hibernate-specific features (`@DynamicUpdate`, `@Cache`, batch processing)

---

### Week 3 — Spring REST & JWT
Covers building RESTful Web Services using Spring Boot and securing them with Spring Security and JWT.

**1. Spring REST — Spring Core & IoC**
- Spring Boot project setup with `@SpringBootApplication`
- Spring XML configuration — `<bean>`, `<constructor-arg>`, `<property>`
- `ClassPathXmlApplicationContext`, `getBean()`
- Logging with `Logger`, `LoggerFactory`, `application.properties` log patterns
- `Country` bean with setter injection
- Singleton vs Prototype scope (`scope="prototype"`)
- List of beans using `<list>` and `<ref bean>`

**2. Spring REST — RESTful Web Services**
- `@RestController`, `@GetMapping`, `@RequestMapping`
- Returning JSON responses (Spring auto-converts beans)
- `CountryController` — `getCountryIndia()`, `getAllCountries()`
- `CountryService` with `@Autowired`, `@PathVariable`
- `CountryNotFoundException` with `@ResponseStatus(NOT_FOUND)`
- MockMvc tests — `@AutoConfigureMockMvc`, `status().isOk()`, `jsonPath()`

**3. Spring REST — Employee & Department REST Services**
- `Employee`, `Department`, `Skill` model classes
- Spring XML config with nested bean references (`employee.xml`)
- `EmployeeDao` with static list loaded from XML
- `EmployeeService`, `EmployeeController` — `GET /employees`
- `DepartmentDao`, `DepartmentService`, `DepartmentController` — `GET /departments`

**4. Spring REST — REST Standards, Validation & Exception Handling**
- REST naming guidelines — plural URLs, class-level `@RequestMapping`
- `@PostMapping` with `@RequestBody`
- Bean validation — `@NotNull`, `@Size`, `@NotBlank`, `@Min`, `@JsonFormat`
- Manual validation with `ValidatorFactory`, `Validator`, `ConstraintViolation`
- `@Valid` annotation replacing manual validation
- `@ControllerAdvice` — `GlobalExceptionHandler` for `MethodArgumentNotValidException`
- `handleHttpMessageNotReadable()` for `InvalidFormatException`
- `@PutMapping` — `updateEmployee()`, `@DeleteMapping` — `deleteEmployee()`
- MockMvc tests for update and delete exception scenarios

**JWT — JWT Authentication**
- `spring-boot-starter-security` dependency setup
- `@EnableWebSecurity`, `WebSecurityConfigurerAdapter`
- In-memory users with `BCryptPasswordEncoder`, roles with `antMatchers`
- JWT concepts — Header, Payload, Signature structure
- `AuthenticationController` — `@GetMapping("/authenticate")`, `@RequestHeader`
- Base64 decoding of Authorization header to extract username
- JWT generation — `JwtBuilder`, `setSubject`, `setIssuedAt`, `setExpiration`, `signWith`
- `JwtAuthorizationFilter` extending `BasicAuthenticationFilter`
- `doFilterInternal()` — Bearer token validation
- `getAuthentication()` — `Jwts.parser()`, `parseClaimsJws()`, `SecurityContextHolder`

---

### Week 4 — Microservices
Covers building and connecting microservices with Spring Boot, including service discovery, inter-service communication, and API gateway routing.

- **microservices-api-gateway** — Gateway routing and request forwarding to downstream services
- **microservices-exercises** — Core microservices hands-on exercises
- **microservices** — Base microservices implementation
- **microservices2** — Extended/additional microservices implementation

---

## 🛠️ Technologies Used

- Java 11 / 17
- Spring Boot 2.7.x / 3.2.x
- Spring Data JPA
- Hibernate ORM
- Spring Security
- JWT (jjwt 0.9.0)
- Spring Cloud (Microservices, API Gateway, Eureka, OpenFeign, Resilience4j)
- JUnit 5, Mockito, SLF4J, Logback
- MySQL
- H2 In-Memory Database
- Maven
- Lombok

---

## 👤 Author

**Saketh Adilla**
Cognizant Digital Nurture 5.0 Program

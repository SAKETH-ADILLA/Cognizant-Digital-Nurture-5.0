# Spring Testing Exercises — Full Solutions

One Spring Boot 3.2.5 / Java 17 Maven project covering all 9 exercises. The
PDF only sketched the production classes and said "Write code for this" for
every test — both the full production code and all 9 tests are implemented
here.

> Built without internet/Maven-repo access in this sandbox, so it hasn't
> been compiled here. Standard, current Spring Boot 3.2 testing stack
> (`@WebMvcTest`, `@DataJpaTest`, `@SpringBootTest`, JUnit 5, Mockito). Run
> `mvn test` once you have normal internet access to fetch dependencies.

## Run all tests

```bash
mvn test
```

## Exercise → file map

| # | Exercise | Production code | Test |
|---|----------|------------------|------|
| 1 | Basic unit test for a service method | `service/CalculatorService.java` | `service/CalculatorServiceTest.java` |
| 2 | Mocking a repository in a service test | `model/User.java`, `repository/UserRepository.java`, `service/UserService.java` | `service/UserServiceTest.java` |
| 3 | Testing a REST controller with MockMvc | `controller/UserController.java` | `controller/UserControllerGetTest.java` |
| 4 | Integration test with Spring Boot | (full stack) | `integration/UserIntegrationTest.java` |
| 5 | Test controller POST endpoint | `UserController.createUser()` | `controller/UserControllerPostTest.java` |
| 6 | Test service exception handling | `UserService.getUserByIdOrThrow()` | `service/UserServiceExceptionTest.java` |
| 7 | Test custom repository query | `UserRepository.findByName()` | `repository/UserRepositoryTest.java` |
| 8 | Test controller exception handling | `exception/GlobalExceptionHandler.java` | `controller/UserControllerExceptionTest.java` |
| 9 | Parameterized test with JUnit | (reuses `CalculatorService`) | `service/CalculatorServiceParameterizedTest.java` |

## Design notes — one deliberate deviation from the PDF

The PDF's `UserController.getUser()` calls `userService.getUserById(id)`
directly and wraps it in `ResponseEntity.ok(...)` — but that method returns
`null` on a miss, which the exercises never actually test, and Exercise 8
asks for a `@ControllerAdvice` that catches `NoSuchElementException` — which
nothing in the PDF's code ever throws.

To make Exercise 8 real rather than a handler that's never exercised, this
solution adds a second service method, `getUserByIdOrThrow(id)`, which
**does** throw `NoSuchElementException` on a miss (via `.orElseThrow(...)`),
and has the controller call that instead. `getUserById(id)` (returning
`null`) is kept exactly as written in the PDF and is still covered by
`UserServiceTest`, for Exercise 2 specifically.

## Test-type cheat sheet (why each exercise uses a different annotation)

- **Plain JUnit** (`CalculatorServiceTest`, `CalculatorServiceParameterizedTest`)
  — no Spring, no Mockito extension; `CalculatorService` has zero dependencies.
- **`@ExtendWith(MockitoExtension.class)` + `@Mock`/`@InjectMocks`**
  (`UserServiceTest`, `UserServiceExceptionTest`) — pure unit test of the
  service layer; the repository is a Mockito mock, no Spring context at all.
- **`@WebMvcTest`** (`UserControllerGetTest`, `UserControllerPostTest`,
  `UserControllerExceptionTest`) — loads only the web layer (controllers +
  `@ControllerAdvice`); the service is a `@MockBean`. Fast, no database.
- **`@DataJpaTest`** (`UserRepositoryTest`) — loads only the JPA layer
  against a real (in-memory H2) database; each test runs in a transaction
  that's rolled back afterward.
- **`@SpringBootTest` + `@AutoConfigureMockMvc`** (`UserIntegrationTest`)
  — boots the entire application context, nothing mocked: real controller,
  real service, real repository, real (H2) database. This is the slowest
  but most realistic test, per Exercise 4's "full flow" requirement.

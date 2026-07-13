# Mocking Dependencies in Spring Tests using Mockito — Full Solutions

One Spring Boot 3.2.5 / Java 17 Maven project covering all 3 exercises. The
production classes (`User`, `UserRepository`, `UserService`,
`UserController`) are exactly as given in the PDF; each exercise's "Write
code for this" test is implemented.

> Built without internet/Maven-repo access in this sandbox, so it hasn't
> been compiled here. Standard, current Spring Boot 3.2 testing stack
> (`@WebMvcTest`, `@SpringBootTest`, JUnit 5, Mockito). Run `mvn test` once
> you have normal internet access to fetch dependencies.

## Run all tests

```bash
mvn test
```

## Exercise → file map

| # | Exercise | What's mocked | Test |
|---|----------|----------------|------|
| 1 | Mocking a service dependency in a controller test | `UserService` (via `@MockBean`) | `controller/UserControllerTest.java` |
| 2 | Mocking a repository in a service test | `UserRepository` (via Mockito `@Mock`) | `service/UserServiceTest.java` |
| 3 | Mocking a service dependency in an integration test | `UserService` (via `@MockBean`), but inside a full `@SpringBootTest` context | `integration/UserIntegrationTest.java` |

## Exercise 1 vs. Exercise 3 — same mock, different scope

Both tests mock `UserService` and hit the same `GET /users/{id}` endpoint
with the same assertions, which can look like duplication — the difference
is entirely in **what Spring boots up around the mock**:

- **Exercise 1** (`@WebMvcTest`) loads *only* the web layer: this one
  controller, `@ControllerAdvice` classes, and MVC infrastructure. Fast,
  minimal context, closest to a true unit test of the controller.
- **Exercise 3** (`@SpringBootTest` + `@AutoConfigureMockMvc`) loads the
  **entire** application context — every `@Component`/`@Service`/`@Repository`
  in the app, full auto-configuration, the real `UserRepository`/database
  wiring available (just not exercised, since the service that would call
  it is mocked). This is closer to what the PDF's hint
  (`@SpringBootTest`, `@AutoConfigureMockMvc`) is pointing at: verifying the
  full Spring wiring works end-to-end, with just the one troublesome/slow
  dependency (the service) swapped out.

## Exercise 2 — no Spring context at all

`UserServiceTest` uses `@ExtendWith(MockitoExtension.class)` with
`@Mock`/`@InjectMocks` — plain Mockito, zero Spring involvement. This is
the fastest and most isolated of the three tests, since `UserService` has
only one dependency (`UserRepository`) and no web/HTTP concerns.

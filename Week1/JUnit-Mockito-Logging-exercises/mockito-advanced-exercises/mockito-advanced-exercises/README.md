# Advanced Mockito Hands-On Exercises

A single Maven project containing full, runnable solutions for all 5 exercises.
The PDF only gave the **test classes**; the production classes they mock
(`Repository`, `Service`, `RestClient`, `ApiService`, `FileReader`,
`FileWriter`, `FileService`, `NetworkClient`, `NetworkService`) are
implemented here so the tests actually compile and pass.

> Built without internet/Maven-repo access in this sandbox, so it hasn't
> been compiled here. It targets JUnit 5.10.2 + Mockito 5.11.0 + Java 17 —
> standard, current versions. Run `mvn test` once you have normal internet
> access to fetch dependencies.

## Structure

```
src/main/java/com/example/
├── Repository.java / Service.java          (Exercise 1 & 5)
├── RestClient.java / ApiService.java       (Exercise 2)
├── FileReader.java / FileWriter.java / FileService.java   (Exercise 3)
└── NetworkClient.java / NetworkService.java (Exercise 4)

src/test/java/com/example/
├── ServiceTest.java
├── ApiServiceTest.java
├── FileServiceTest.java
├── NetworkServiceTest.java
└── MultiReturnServiceTest.java
```

## Run all tests

```bash
mvn test
```

## Design notes

- Every collaborator (`Repository`, `RestClient`, `FileReader`, `FileWriter`,
  `NetworkClient`) is an **interface**. Mockito can mock concrete classes too,
  but interfaces keep the mocks simple and make the intent — "this is a
  swappable boundary to an external dependency" — explicit.
- `FileReader`/`FileWriter` are custom interfaces in `com.example`, not
  `java.io.FileReader`/`FileWriter` — using the JDK's I/O classes directly
  would mean real file-system calls; the whole point of Exercise 3 is to
  mock file I/O away entirely.
- `Service`, `ApiService`, `FileService`, and `NetworkService` all follow
  the same shape: a single collaborator injected via constructor, and one
  method that calls the collaborator and wraps the result in a string
  prefix (`"Processed "`, `"Fetched "`, `"Connected to "`) — exactly what
  each test's `assertEquals(...)` expects.
- `MultiReturnServiceTest` (Exercise 5) reuses the same `Service`/`Repository`
  pair from Exercise 1 — Mockito's `.thenReturn(a).thenReturn(b)` chaining
  is what varies the return value across consecutive calls, not the
  production code.

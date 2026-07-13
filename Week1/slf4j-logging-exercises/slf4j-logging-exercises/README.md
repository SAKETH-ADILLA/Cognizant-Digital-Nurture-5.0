# Logging using SLF4J — Full Solutions

One Maven project (plain Java, no Spring needed for this topic) covering
all 3 exercises. `slf4j-api` and `logback-classic` versions match exactly
what the PDF specifies.

> Built without internet/Maven-repo access in this sandbox, so it hasn't
> been compiled here. Run `mvn compile` once you have normal internet
> access to fetch dependencies.

## Run each exercise

```bash
mvn compile

mvn exec:java -Dexec.mainClass="com.example.logging.LoggingExample"
mvn exec:java -Dexec.mainClass="com.example.logging.ParameterizedLoggingExample"
mvn exec:java -Dexec.mainClass="com.example.logging.AppenderLoggingExample"
```

(Or just run each `main()` directly from your IDE.)

## Exercise → file map

| # | Exercise | File |
|---|----------|------|
| 1 | Logging error messages and warning levels | `LoggingExample.java` |
| 2 | Parameterized logging | `ParameterizedLoggingExample.java` |
| 3 | Using different appenders | `logback.xml` + `AppenderLoggingExample.java` |

## What each exercise demonstrates

- **Exercise 1** — the two levels named in the task (`error`, `warn`), plus
  `info`/`debug` thrown in so you can see SLF4J's severity ordering
  (`TRACE < DEBUG < INFO < WARN < ERROR`) in the console output.
- **Exercise 2** — `{}` placeholder syntax instead of string concatenation
  (`logger.info("User {} logged in", username)`), including the pattern
  where a trailing `Throwable` argument is auto-detected and its stack
  trace gets logged alongside the formatted message.
- **Exercise 3** — `logback.xml` wires **one root logger to two appenders**
  (`ConsoleAppender` + `FileAppender`), so every log call in
  `AppenderLoggingExample` is written to stdout *and* to `app.log` (created
  in the working directory) at the same time. Logback picks up
  `src/main/resources/logback.xml` automatically — `AppenderLoggingExample`
  itself doesn't reference it directly, it's just running against whatever
  config Logback finds on the classpath.

## A version note

`slf4j-api 1.7.30` and `logback-classic 1.2.3` (as specified in the PDF)
are from 2020 and still work fine for these exercises, but they're no
longer the current releases. For a new project outside of this exercise,
prefer current versions instead — `slf4j-api 2.0.x` and
`logback-classic 1.5.x` — which include performance improvements and
security fixes. Swapping versions in `pom.xml` is safe here; none of the
three exercises use any API that changed between those versions.

package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Exercise 2: Parameterized Logging.
//
// Using "{}" placeholders instead of string concatenation (e.g.
// "User " + name + " logged in") is the recommended SLF4J style: the
// message is only built if the log level is actually enabled, avoiding
// wasted string concatenation work at levels that get filtered out.
public class ParameterizedLoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    public static void main(String[] args) {
        String username = "Alice";
        int loginAttempts = 3;

        // Single parameter
        logger.info("User {} logged in", username);

        // Multiple parameters
        logger.warn("User {} has failed to log in {} times", username, loginAttempts);

        // Parameterized logging also works cleanly with exceptions -
        // the Throwable is detected automatically as the last argument
        // and its stack trace is logged, while {} placeholders are still
        // filled from the earlier arguments.
        try {
            int result = 10 / 0;
        } catch (ArithmeticException ex) {
            logger.error("Failed to process division for user {}", username, ex);
        }
    }
}

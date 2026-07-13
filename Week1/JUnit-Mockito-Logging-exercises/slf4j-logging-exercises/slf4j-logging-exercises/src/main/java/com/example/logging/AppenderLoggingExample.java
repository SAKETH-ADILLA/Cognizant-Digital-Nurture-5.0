package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Exercise 3: Using Different Appenders.
//
// This class doesn't configure appenders itself - Logback picks up
// src/main/resources/logback.xml automatically from the classpath at
// startup. That config wires ONE root logger to TWO appenders
// ("console" and "file"), so every log call below is written to both
// stdout AND app.log (created in the working directory) simultaneously.
public class AppenderLoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(AppenderLoggingExample.class);

    public static void main(String[] args) {
        logger.debug("Application starting up - this line goes to both console and app.log");
        logger.info("Processing started");
        logger.warn("Cache is running low on space");
        logger.error("Failed to connect to downstream service");

        System.out.println("Done. Check app.log in the working directory - it should contain the same 4 lines shown above.");
    }
}

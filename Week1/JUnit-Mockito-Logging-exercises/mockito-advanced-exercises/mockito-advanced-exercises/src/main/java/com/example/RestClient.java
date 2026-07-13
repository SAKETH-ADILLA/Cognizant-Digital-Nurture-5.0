package com.example;

// Exercise 2: Mocking External Services (RESTful APIs).
// Represents a thin wrapper around an outbound HTTP call to a third-party
// REST API. Kept as an interface so tests never make a real network call.
public interface RestClient {
    String getResponse();
}

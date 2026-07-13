package com.example;

// Exercise 4: Mocking Network Interactions.
// Represents a low-level network connection abstraction (sockets, RPC
// channel, etc.) - kept as an interface so tests don't touch real network resources.
public interface NetworkClient {
    String connect();
}

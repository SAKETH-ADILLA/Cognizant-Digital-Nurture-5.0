package com.example;

// Exercise 1: Mocking Databases and Repositories.
// Represents a data-access boundary - in a real app this would talk to a
// database. Kept as an interface so it's easy to mock with Mockito.
public interface Repository {
    String getData();
}

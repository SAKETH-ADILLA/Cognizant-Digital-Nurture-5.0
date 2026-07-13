package com.example;

// Exercise 1 & 5: consumes a Repository and applies some business logic
// on top of whatever it returns.
public class Service {

    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public String processData() {
        return "Processed " + repository.getData();
    }
}

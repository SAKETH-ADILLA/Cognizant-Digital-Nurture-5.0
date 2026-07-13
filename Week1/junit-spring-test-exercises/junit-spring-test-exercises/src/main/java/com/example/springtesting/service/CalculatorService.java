package com.example.springtesting.service;

import org.springframework.stereotype.Service;

// Exercise 1: trivial service, no dependencies - the simplest possible unit test target.
@Service
public class CalculatorService {

    public int add(int a, int b) {
        return a + b;
    }
}

package com.example.springtesting.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Exercise 1: Basic Unit Test for a Service Method.
// No Spring context needed at all - CalculatorService has zero dependencies,
// so it's instantiated directly with "new".
class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    void testAdd() {
        int result = calculatorService.add(2, 3);
        assertEquals(5, result);
    }

    @Test
    void testAddWithNegativeNumbers() {
        int result = calculatorService.add(-4, 4);
        assertEquals(0, result);
    }
}

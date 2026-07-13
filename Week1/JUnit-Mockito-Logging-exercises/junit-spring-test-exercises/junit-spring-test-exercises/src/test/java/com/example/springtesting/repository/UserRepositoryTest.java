package com.example.springtesting.repository;

import com.example.springtesting.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Exercise 7: Test Custom Repository Query.
// @DataJpaTest spins up just the JPA layer against an in-memory H2 database
// (auto-configured, transactional, rolled back after each test) - no web
// layer, no full application context.
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByName() {
        userRepository.save(new User(null, "Charlie"));
        userRepository.save(new User(null, "Charlie"));
        userRepository.save(new User(null, "Dana"));

        List<User> results = userRepository.findByName("Charlie");

        assertEquals(2, results.size());
        results.forEach(u -> assertEquals("Charlie", u.getName()));
    }

    @Test
    void testFindByName_noMatch() {
        userRepository.save(new User(null, "Dana"));

        List<User> results = userRepository.findByName("Nonexistent");

        assertEquals(0, results.size());
    }
}

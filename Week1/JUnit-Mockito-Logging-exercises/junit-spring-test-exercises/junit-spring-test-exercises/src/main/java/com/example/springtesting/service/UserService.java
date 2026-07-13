package com.example.springtesting.service;

import com.example.springtesting.model.User;
import com.example.springtesting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Exercise 2: as given in the exercise - returns null if not found.
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Exercise 6 & 8: throwing variant, used by the controller so a missing
    // user turns into a NoSuchElementException that GlobalExceptionHandler can catch.
    public User getUserByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    // Exercise 5: used by the POST /users endpoint
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}

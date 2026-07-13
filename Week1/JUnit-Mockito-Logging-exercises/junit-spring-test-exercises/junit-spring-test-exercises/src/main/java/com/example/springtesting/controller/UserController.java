package com.example.springtesting.controller;

import com.example.springtesting.model.User;
import com.example.springtesting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Exercise 3: GET a single user. Uses the throwing service method so a
    // missing id surfaces as NoSuchElementException -> handled by
    // GlobalExceptionHandler (Exercise 8) instead of returning a null body.
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserByIdOrThrow(id);
        return ResponseEntity.ok(user);
    }

    // Exercise 5: create a user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }
}

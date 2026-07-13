package com.example.springtesting.service;

import com.example.springtesting.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

// Exercise 6: Test Service Exception Handling.
// Verifies that getUserByIdOrThrow surfaces a NoSuchElementException
// (rather than silently returning null) when the repository has no match.
@ExtendWith(MockitoExtension.class)
class UserServiceExceptionTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserByIdOrThrow_throwsWhenMissing() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> userService.getUserByIdOrThrow(99L)
        );

        assertEquals("User not found with id: 99", exception.getMessage());
    }
}

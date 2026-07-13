package com.example.springtesting.controller;

import com.example.springtesting.exception.GlobalExceptionHandler;
import com.example.springtesting.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Exercise 8: Test Controller Exception Handling.
// @WebMvcTest automatically picks up @ControllerAdvice classes in addition
// to the targeted controller, so GlobalExceptionHandler is active here
// without needing to import it explicitly as a bean.
@WebMvcTest(controllers = UserController.class)
class UserControllerExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testUserNotFound_returns404WithHandlerMessage() throws Exception {
        when(userService.getUserByIdOrThrow(99L))
                .thenThrow(new NoSuchElementException("User not found with id: 99"));

        mockMvc.perform(get("/users/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }
}

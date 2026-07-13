package com.example.mockdeps.controller;

import com.example.mockdeps.model.User;
import com.example.mockdeps.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Exercise 1: Mocking a Service Dependency in a Controller Test.
// @WebMvcTest loads only the web layer (UserController); UserService is
// replaced with a Mockito mock via @MockBean, so no real service or
// repository logic runs - only the controller's request/response handling
// is under test.
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetUser_found() throws Exception {
        User user = new User(1L, "Alice");
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    void testGetUser_notFound() throws Exception {
        when(userService.getUserById(99L)).thenReturn(null);

        // Controller returns ResponseEntity.ok(null) as written in the
        // exercise - still a 200 with an empty body, since the controller
        // itself does no null-check.
        mockMvc.perform(get("/users/99"))
                .andExpect(status().isOk());
    }
}

package com.example.mockdeps.integration;

import com.example.mockdeps.model.User;
import com.example.mockdeps.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Exercise 3: Mocking a Service Dependency in an Integration Test.
// Unlike a @WebMvcTest, @SpringBootTest boots the FULL application context
// (real controller, real service bean slot, real repository/database
// wiring available) - but @MockBean still swaps UserService for a mock,
// same as Exercise 1. The difference from Exercise 1 is what's under test:
// here the whole Spring context wiring (auto-configuration, dispatcher
// servlet, JSON conversion, etc.) is exercised end-to-end, not just the
// isolated MVC layer.
@SpringBootTest
@AutoConfigureMockMvc
class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetUser_withMockedService() throws Exception {
        User user = new User(1L, "Integration Test User");
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Integration Test User"));
    }
}

package com.example.springtesting.integration;

import com.example.springtesting.model.User;
import com.example.springtesting.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Exercise 4: Integration Test with Spring Boot.
// @SpringBootTest boots the FULL application context (controller, service,
// repository, and a real H2 database) - nothing is mocked, unlike the
// @WebMvcTest in Exercise 3. This exercises the entire request path:
// HTTP request -> controller -> service -> repository -> database -> back.
@SpringBootTest
@AutoConfigureMockMvc
class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFullFlowFromControllerToDatabase() throws Exception {
        User saved = userRepository.save(new User(null, "Integration Test User"));

        mockMvc.perform(get("/users/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(saved.getId()))
                .andExpect(jsonPath("$.name").value("Integration Test User"));
    }
}

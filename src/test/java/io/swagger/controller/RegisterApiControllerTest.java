package io.swagger.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.User;
import io.swagger.model.dto.RegisterDTO;
import io.swagger.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class RegisterApiControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthenticationService service;
    private RegisterDTO registerDTO;

    @BeforeEach
    public void setup() {
        registerDTO = new RegisterDTO("JD0001", "Wachtwoord1", "John", "Doe",
                "JohnDoe@gmail.com", "06 12345678");
    }


    @Test
    void registerUserShouldReturnCreated() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        this.mvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(registerDTO)))
                .andExpect(status().isCreated());

    }
}
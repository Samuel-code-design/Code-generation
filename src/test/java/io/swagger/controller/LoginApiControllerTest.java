package io.swagger.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.dto.LoginDTO;
import io.swagger.model.dto.RegisterDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class LoginApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LoginDTO loginDTO;

    @BeforeEach
    public void setup() {
        loginDTO = new LoginDTO("JD0001", "Wachtwoord1#");
    }

    @Test
    void loginUserShouldReturnOk() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        this.mvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void wrongLoginUserShouldReturnError() throws Exception {
        LoginDTO wrongLogin = new LoginDTO("test", "Wachtwoord1#");
        ObjectMapper mapper = new ObjectMapper();
        this.mvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(wrongLogin)))
                .andExpect(status().isUnprocessableEntity());
    }

}
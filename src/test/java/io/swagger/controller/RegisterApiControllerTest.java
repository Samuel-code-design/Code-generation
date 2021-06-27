package io.swagger.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.User;
import io.swagger.model.dto.RegisterDTO;
import io.swagger.repository.UserRepository;
import io.swagger.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class RegisterApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RegisterDTO registerDTO;


    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        registerDTO = new RegisterDTO("test", "Wachtwoord1", "John", "Doe",
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
    @Test
    void registerUserUsernameTakenShouldReturnUnprocessableEntity() throws Exception {
        RegisterDTO registerDTOTaken = new RegisterDTO("JD0001", "Wachtwoord1", "John", "Doe",
                "JohnDoe@gmail.com", "06 12345678");
        ObjectMapper mapper = new ObjectMapper();
        this.mvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(registerDTOTaken)))
                .andExpect(status().isUnprocessableEntity());
    }
    @Test
    void registerUserEmailTakenShouldReturnUnprocessableEntity() throws Exception {
        RegisterDTO registerDTOTaken = new RegisterDTO("a", "Wachtwoord1", "John", "Doe",
                "samuel11hoi@gmail.com", "06 12345678");
        ObjectMapper mapper = new ObjectMapper();
        this.mvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(registerDTOTaken)))
                .andExpect(status().isUnprocessableEntity());
    }
    @Test
    void registerUserPasswordShortShouldReturnUnprocessableEntity() throws Exception {
        RegisterDTO registerDTOTaken = new RegisterDTO("test2", "e", "John", "Doe",
                "test2@gmail.com", "06 12345678");
        ObjectMapper mapper = new ObjectMapper();
        this.mvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(registerDTOTaken)))
                .andExpect(status().isUnprocessableEntity());
    }
}
package io.swagger.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.api.ResponseExceptionHandler;
import io.swagger.model.User;
import io.swagger.model.dto.RegisterDTO;
import io.swagger.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;
import org.assertj.core.api.AbstractAssert;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.anyString;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AuthenticationServiceTest {

    @MockBean
    private UserRepository userRepository;
    private AuthenticationService authenticationService;
    private ApplicationRunner applicationRunner;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void willThrowWhenEmailIsTaken() {
        RegisterDTO registerDTO = new RegisterDTO("JD0001", "w#", "John", "Doe",
                "JohnDoe@gmail.com", "06 12345678");
        given(userRepository.existsByEmail(anyString())).willReturn(true);

        assertThatThrownBy(() -> authenticationService.signup(modelMapper.map(registerDTO, User.class))
               .contains("Email already in use, please try again and choose a different one"));
        verify(userRepository, never()).save(any());

    }
    @Test
    public void willThrowWhenUsernameIsTaken() {
        RegisterDTO registerDTO = new RegisterDTO("JD0001", "w#", "John", "Doe",
                "JohnDoe@gmail.com", "06 12345678");
        given(userRepository.existsByUsername(anyString())).willReturn(true);

        assertThatThrownBy(() -> authenticationService.signup(modelMapper.map(registerDTO, User.class))
                .contains("Username is already in use, please try again and choose a different one"));
        verify(userRepository, never()).save(any());

    }
    @Test
    public void willThrowWhenPasswordIsShort() {
        RegisterDTO registerDTO = new RegisterDTO("JD0001", "w#", "John", "Doe",
                "JohnDoe@gmail.com", "06 12345678");

        assertThatThrownBy(() -> authenticationService.signup(modelMapper.map(registerDTO, User.class)).contains("Password length to short, minimum length 7"));
        verify(userRepository, never()).save(any());
    }

}
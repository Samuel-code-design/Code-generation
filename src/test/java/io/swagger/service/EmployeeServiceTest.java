package io.swagger.service;
import io.swagger.configuration.ApplicationRunner;
import io.swagger.model.Role;
import io.swagger.model.User;
import io.swagger.model.dto.CreateUserDTO;
import io.swagger.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class EmployeeServiceTest {

    @MockBean
    private UserRepository repository;
    private EmployeeService service;
    private List<Role> roles;


    @Autowired
    private ModelMapper modelMapper;


    @BeforeEach
    public void Setup() {
        roles = new ArrayList<>();
        roles.add(Role.ROLE_EMPLOYEE);
    }

    @Test
    public void willThrowExceptionWhenUsernameIsTaken() {
        CreateUserDTO dto = new CreateUserDTO( "JD0001", "Wachtwoord1#", "Samuel", "brouwer", "samuel11hoi@gmail.com", "06 12345678", 1000L, 100L
                , roles);

        given(repository.existsByUsername(anyString())).willReturn(true);

        Exception exception = assertThrows(ResponseStatusException.class, () -> service.createUser(dto));

        assertEquals("Username is already in use, please try again and choose a different one", exception.getMessage());
    }

    @Test
    void getUserById() {
        User u = new User(1L,"JD0001", "Wachtwoord1#", "Samuel", "brouwer", "samuel11hoi@gmail.com", "06 12345678", roles, false
                , 1000L, 1000L);

        when(repository.findByIdEquals(any())).thenReturn(u);

        User user = service.userById(1L);

        assertEquals(u, user);
    }

}
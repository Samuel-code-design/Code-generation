package io.swagger.service;

import io.swagger.model.Role;
import io.swagger.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private User user;

    @InjectMocks
    private  EmployeeService service;

    @BeforeEach
    public void Setup() {

        List<Role> roles = new ArrayList<>();
        roles.add(Role.ROLE_EMPLOYEE);

        user = new User(1L, "JD0001", "Wachtwoord1#", "Samuel", "brouwer", "samuel11hoi@gmail.com", "06 12345678", roles, false
                , 1000L, 1000L);
    }

    @Test
    void createUser() {
    }

    @Test
    void lockUserById() {

    }

    @Test
    void updateUser() {

    }

    @Test
    void getUsers() {

    }

    @Test
    void userByIdWith() {

    }
}
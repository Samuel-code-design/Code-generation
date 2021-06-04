package io.swagger.controller;

import io.swagger.model.Role;
import io.swagger.model.User;
import io.swagger.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;


import java.util.List;



@SpringBootTest
@AutoConfigureMockMvc
class UsersApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService service;
    private User u;

    @BeforeEach
    public void setup() {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.ROLE_EMPLOYEE);

         u = new User("JD0001", "Wachtwoord1#", "Samuel", "brouwer", "samuel11hoi@gmail.com", "06 12345678", roles, false
                 , 1000L, 1000L);
    }

    @Test
    void GetUsersShouldReturnForbiddenStatus() throws Exception {
        given(service.getUsers("")).willReturn(Arrays.asList(u));
        this.mvc.perform(get("/users")).andExpect(
                status().isForbidden());
    }

    @Test
    void lockUserByIdShouldReturnJsonArray() throws Exception {
        given(service.getUsers("")).willReturn(Arrays.asList(u));
        this.mvc.perform(put("/users/{id}")).andExpect(
                status().isOk())
                .andExpect(jsonPath("$[0].dayLimit").value(u.getDayLimit()));
    }

    @Test
    void updateUserShouldReturnJsonArray() throws Exception {
        given(service.getUsers("")).willReturn(Arrays.asList(u));
        this.mvc.perform(put("/users")).andExpect(
                status().isOk())
                .andExpect(jsonPath("$[0].dayLimit").value(u.getDayLimit()));
    }

    @Test
    void createUserShouldReturnJsonArray() throws Exception {
        given(service.getUsers("")).willReturn(Arrays.asList(u));
        this.mvc.perform(post("/users")).andExpect(
                status().isOk())
                .andExpect(jsonPath("$[0].dayLimit").value(u.getDayLimit()));
    }

    @Test
    void userByIdShouldReturnJsonArray() throws Exception {
        given(service.getUsers("")).willReturn(Arrays.asList(u));
        this.mvc.perform(get("/users/{id}")).andExpect(
                status().isOk())
                .andExpect(jsonPath("$[0].dayLimit").value(u.getDayLimit()));
    }
}
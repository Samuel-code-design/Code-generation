package io.swagger.controller;

import io.swagger.model.User;
import io.swagger.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsersApiControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService service;
    private User u;

    @BeforeEach
    public void setup() {
         u = new User();
    }

    @Test
    void GetUsersShouldReturnJsonArray() throws Exception {
        given(service.getUsers("")).willReturn(Arrays.asList(u));
        this.mvc.perform(get("/guitars")).andExpect(
                status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].brand").value(u.getUsername()));
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
    void userById() {
    }
}
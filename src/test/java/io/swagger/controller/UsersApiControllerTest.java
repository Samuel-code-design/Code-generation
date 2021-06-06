package io.swagger.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Role;
import io.swagger.model.User;
import io.swagger.model.dto.CreateUserDTO;
import io.swagger.service.EmployeeService;
import io.swagger.v3.oas.annotations.Parameter;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;


import java.util.List;



@SpringBootTest(properties = {"security.basic.enabled=false"})
@AutoConfigureMockMvc
class UsersApiControllerTest {


    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    private EmployeeService service;

    private User u;
    private User lockedUser;
    List<Role> roles;

    @Autowired
    private MockMvc mvc;


    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        roles = new ArrayList<>();
        roles.add(Role.ROLE_EMPLOYEE);

         u = new User(1L, "JD0001", "Wachtwoord1#", "Samuel", "brouwer", "samuel11hoi@gmail.com", "06 12345678", roles, false
                 , 1000L, 1000L);

         lockedUser = new User(1L, "JD0001", "Wachtwoord1#", "Samuel", "brouwer", "samuel11hoi@gmail.com", "06 12345678", roles, true
                , 1000L, 1000L);
    }

    @Test
    void GetUsersShouldReturnJsonArray() throws Exception {
        given(service.getUsers("J")).willReturn(Arrays.asList(u));

        this.mvc.perform(get("/users?searchstring=J")).andExpect(
                status().isOk())
                .andExpect(jsonPath("$[0].dayLimit").value(u.getDayLimit()));
    }

    @Test
    void GetUsersWithoutParamShouldReturnJsonArray() throws Exception {
        given(service.getUsers(null)).willReturn(Arrays.asList(u));
        this.mvc.perform(get("/users")).andExpect(
                status().isOk())
                .andExpect(jsonPath("$[0].dayLimit").value(u.getDayLimit()));
    }

    @Test
    void lockingLockedUserShouldReturnStatusOk() throws Exception {
        // url = "/users/{id}";
        given(service.lockUserById(1L)).willReturn(lockedUser);
        this.mvc.perform(put("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    void lockingLockedUserShouldReturnJason() throws Exception {
        given(service.lockUserById(1L)).willReturn(lockedUser);
        this.mvc.perform(put("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.locked").value(lockedUser.getLocked()));
    }

    @Test
    void lockingLockedUserShouldReturnJsonLockedMustBeTrue() throws Exception {
//        String url = "/users/{id}";
        given(service.lockUserById(1L)).willReturn(lockedUser);

        MvcResult result =  this.mvc.perform(put("/users/1"))
                .andExpect(status().isOk())
                .andReturn();

        assert (result.getResponse().getContentAsString().contains("true"));
    }


    @Test
    void updateUserShouldReturnStatusOk() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        u.setFirstName("test");

        given(service.updateUser(u)).willReturn(u);

        this.mvc.perform(put("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(u)))
                .andExpect(status().isOk());
    }


    @Test
    void createUserShouldReturn201Status() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        this.mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(u)))
                .andExpect(status().isCreated());
    }

    @Test
    void userByIdShouldReturnStatusOk() throws Exception {
//        String url = "/users/{id}";
        given(service.userById(1L)).willReturn(u);
        this.mvc.perform(get("/users/1"))
                .andExpect(status().isOk());
    }

    @Test
    void userByIdShouldReturnJson() throws Exception {
//        String url = "/users/{id}";
        given(service.userById(1L)).willReturn(u);
        this.mvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dayLimit").value(u.getDayLimit()));
    }
}
package io.swagger.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Account;
import io.swagger.model.AccountType;
import io.swagger.model.Role;
import io.swagger.model.User;
import io.swagger.model.dto.AccountCreateDTO;
import io.swagger.model.dto.AccountResponseDTO;
import io.swagger.model.dto.AccountUpdateDTO;
import io.swagger.model.dto.LoginDTO;
import io.swagger.models.Response;
import io.swagger.service.AccountService;
import io.swagger.service.AuthenticationService;
import io.swagger.service.MyUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AccountsApiControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private LoginDTO loginDTO;

    private User u;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "EMPLOYEE")
    void addAccountShouldReturn201Created() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        AccountCreateDTO acc = new AccountCreateDTO(AccountType.CURRENT, 0.0, false, 3L);
        this.mvc
                .perform(
                        post("/accounts")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(mapper.writeValueAsString(acc)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "EMPLOYEE")
    void allAccountsShouldReturnOK() throws Exception {
        this.mvc.perform(get("/accounts"))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "EMPLOYEE")
    void findAccountByIbanShouldReturnOK() throws Exception {
        this.mvc.perform(get("/account/NL02INHO0123456789"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "EMPLOYEE")
    void findAccountsByUserIdShouldReturnOK() throws Exception{
        this.mvc.perform(get("/accounts/2"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "EMPLOYEE")
    void lockAccountShouldReturnOK() throws Exception{
        this.mvc.perform(put("/accounts/NL02INHO0123456789"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "EMPLOYEE")
    void updateAccountShouldReturnOK() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        AccountUpdateDTO acc = new AccountUpdateDTO("NL02INHO0123456789", AccountType.SAVING, 0.0, false, 3L);
        this.mvc
                .perform(
                        put("/accounts")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(mapper.writeValueAsString(acc)))
                .andExpect(status().isOk());
    }
}
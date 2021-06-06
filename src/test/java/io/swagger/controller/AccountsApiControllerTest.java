package io.swagger.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Account;
import io.swagger.model.AccountType;
import io.swagger.model.Role;
import io.swagger.model.User;
import io.swagger.model.dto.AccountCreateDTO;
import io.swagger.model.dto.AccountResponseDTO;
import io.swagger.model.dto.AccountUpdateDTO;
import io.swagger.models.Response;
import io.swagger.service.AccountService;
import io.swagger.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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

@SpringBootTest(properties = {"security.basic.enabled=false"})
@AutoConfigureMockMvc(addFilters = false)
class AccountsApiControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mvc;

    @MockBean private AccountService accountService;
    @MockBean private AuthenticationService authService;
    private Account account;
    private User u;


    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        List<Role> roles = new ArrayList<>();
        roles.add(Role.ROLE_EMPLOYEE);

        u = new User("JulieMeij", "1234567", "Julie", "van der Meij", "juliemeij@gmail.com", "06 12345678",
                roles, false, true, 1000L, 1000L);

        account = new Account("NL02INHO0987654321", AccountType.CURRENT, 0.0, 0.0, false, u);

    }

    @Test
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
    void allAccountsShouldReturnOK() throws Exception {
        this.mvc.perform(get("/accounts"))
                .andExpect(status().isOk());

    }

    @Test
    void findAccountByIbanShouldReturnFOUND() throws Exception {
        this.mvc.perform(get("/account/NL02INHO0987654321"))
                .andExpect(status().isFound());
    }

    @Test
    void findAccountsByUserIdShouldReturnFOUND() throws Exception{
        this.mvc.perform(get("/accounts/1"))
                .andExpect(status().isFound());
    }

    @Test
    void lockAccountShouldReturnOK() throws Exception{
        this.mvc.perform(put("/accounts/NL02INHO0987654321"))
                .andExpect(status().isOk());
    }

    @Test
    void updateAccount() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        AccountUpdateDTO acc = new AccountUpdateDTO("NL02INHO0987654321", AccountType.SAVING, 0.0, false, 3L);
        this.mvc
                .perform(
                        put("/accounts")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(mapper.writeValueAsString(acc)))
                .andExpect(status().isOk());
    }
}
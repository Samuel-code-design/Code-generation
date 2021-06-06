package io.swagger.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.model.NewTransaction;
import io.swagger.model.Transaction;
import io.swagger.service.TransactionService;
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

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {"security.basic.enabled=false"})
@AutoConfigureMockMvc
public class TransactionApiControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    private TransactionService transactionService;
    private Transaction t;
    private MockMvc mvc;

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        t = new Transaction(new NewTransaction(10.00, LocalDateTime.now(), "NL01INHO0123456789", "NL02INHO0123456789", 1L));
    }

    @Test
    void GetTransactionsShouldReturnJsonArray() throws Exception {
        given(transactionService.getAllTransactions()).willReturn(Arrays.asList());

        this.mvc.perform(get("/Customers/Transactions")).andExpect(
                status().isOk());
    }

    @Test
    void AddTransactionShouldReturnStatusOk() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        System.out.println(mapper.writeValueAsString(t.getTimestamp()));
        this.mvc.perform(post("/Transactions").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(t)))
                .andExpect(status().isOk());
    }
}

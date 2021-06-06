package io.swagger.IT.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.swagger.model.AccountType;
import io.swagger.model.dto.AccountCreateDTO;
import io.swagger.model.dto.AccountUpdateDTO;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class AccountStepDefs {
    HttpHeaders headers = new HttpHeaders();
    String baseUrl = "https://localhost:8080/api/swagger-ui/accounts";
    String singleUrl = "https://localhost:8080/api/swagger-ui/account/";

    RestTemplate template = new RestTemplate();
    ResponseEntity<String> responseEntity;
    String response;

    @When("I retrieve all accounts")
    public void iRetrieveAllAccounts()throws URISyntaxException {
        URI uri = new URI(baseUrl);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = template.getForEntity(uri, String.class);
    }

    @When("I retrieve account with iban {string}")
    public void IGetOneAccountWithIban(String iban)throws URISyntaxException {
        URI uri = new URI(singleUrl + iban);
        responseEntity = template.getForEntity(uri, String.class);
    }

    @When("I post an account")
    public void iPostAnAccount() throws JsonProcessingException, URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();
        AccountCreateDTO acc = new AccountCreateDTO(AccountType.CURRENT, 0.0, false, 1L);
        URI uri = new URI(baseUrl);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(acc), headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
    }

    @When("I update an account")
    public void iUpdateAnAccount()throws JsonProcessingException, URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();
        AccountUpdateDTO acc = new AccountUpdateDTO("NL02INHO0123456789", AccountType.SAVING, 20.0, true, 1L);
        URI uri = new URI(baseUrl);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(acc), headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
    }

    @When("I get accounts for userId {long}")
    public void IGetOneAccountWithIban(Long userId)throws URISyntaxException {
        URI uri = new URI(baseUrl + userId);
        responseEntity = template.getForEntity(uri, String.class);
    }

    @Then("I get a list of {int} accounts")
    public void iGetAListOfGuitars(int size) throws JSONException {
        response = responseEntity.getBody();
        JSONArray array = new JSONArray(response);
        Assert.assertEquals(size, array.length());
    }

}

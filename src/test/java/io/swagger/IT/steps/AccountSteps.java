package io.swagger.IT.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.swagger.model.Account;
import io.swagger.model.AccountType;
import io.swagger.model.dto.AccountCreateDTO;
import io.swagger.model.dto.AccountUpdateDTO;
import io.swagger.model.dto.LoginDTO;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;

public class AccountSteps {
    private HttpHeaders headers = new HttpHeaders();
    private String baseUrl = "http://localhost:8080/api/accounts";
    private String singleUrl = "http://localhost:8080/api/account/";

    private RestTemplate template = new RestTemplate();
    private ResponseEntity<String> responseEntity;
    private String token = getToken();

    private String response;

    public AccountSteps() throws URISyntaxException, JSONException {
    }

    public String getToken() throws URISyntaxException, JSONException {
        URI uri = new URI("http://localhost:8080/api/login");

        LoginDTO login = new LoginDTO("JD0001", "Wachtwoord1#");

        HttpEntity<LoginDTO> entity = new HttpEntity<>(login, headers);

        responseEntity = template.postForEntity(uri, entity, String.class);
        return responseEntity.getBody();
    }

    @When("I retrieve all accounts")
    public void iRetrieveAllAccounts() throws URISyntaxException, JSONException {
        URI uri = new URI(baseUrl);
        headers.add("Authorization", token);
        Account account = new Account();
        HttpEntity<Account> entity = new HttpEntity<>(account, headers);
        responseEntity = template.exchange(uri, HttpMethod.GET, entity, String.class);
    }

    @When("I retrieve account with iban {string}")
    public void iGetOneAccountWithIban(String iban)throws URISyntaxException {
        URI uri = new URI(singleUrl + iban);
        headers.add("Authorization", token);
        Account account = new Account();
        HttpEntity<Account> entity = new HttpEntity<>(null, headers);
        try{
            responseEntity = template.exchange(uri, HttpMethod.GET, entity, String.class);
        }catch (HttpClientErrorException ex){
            Assert.assertEquals(true, ex.getResponseBodyAsString().contains("No account found for this iban"));
            responseEntity = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    @When("I post an account")
    public void iPostAnAccount() throws JsonProcessingException, URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();
        AccountCreateDTO acc = new AccountCreateDTO(AccountType.CURRENT, 0.0, false, 2L);
        URI uri = new URI(baseUrl);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(acc), headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
    }

    @When("I update an account with iban {string}")
    public void iUpdateAnAccount(String iban)throws JsonProcessingException, URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();
        AccountUpdateDTO acc = new AccountUpdateDTO(iban, AccountType.SAVING, 20.0, false, 3L);
        URI uri = new URI(baseUrl);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(acc), headers);
        try{
            responseEntity = template.exchange(uri, HttpMethod.PUT, entity, String.class);
        }catch (HttpClientErrorException ex){
            Assert.assertEquals(true, ex.getResponseBodyAsString().contains("IBAN does not exist"));
            responseEntity = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    @When("I get accounts for userId {long}")
    public void iGetAccountsForUserId(Long userId)throws URISyntaxException {
        URI uri = new URI(baseUrl + "/" + userId);
        headers.add("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        try{
            responseEntity = template.exchange(uri, HttpMethod.GET, entity, String.class);
        }catch (HttpClientErrorException ex){
            Assert.assertEquals(true, ex.getResponseBodyAsString().contains("No user with this id"));
            responseEntity = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }
    @When("I lock an account with iban {string}")
    public void iLockAnAccountWithIban(String iban) throws URISyntaxException {
        URI uri = new URI(baseUrl + "/" + iban);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        try{
            responseEntity = template.exchange(uri, HttpMethod.PUT, entity, String.class);
        }catch (HttpClientErrorException ex){
            Assert.assertEquals(true, ex.getResponseBodyAsString().contains("No account found for this Iban"));
            responseEntity = new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    @Then("I get a list of {int} accounts")
    public void iGetAListOfAccounts(int size) throws JSONException {
        response = responseEntity.getBody();
        JSONArray array = new JSONArray(response);
        Assert.assertEquals(size, array.length());
    }

    @Then("\\(Account) I get http status {int}")
    public void accountIGetHttpStatus(int expected) {
        int response = responseEntity.getStatusCodeValue();
        Assert.assertEquals(expected, response);
    }

}

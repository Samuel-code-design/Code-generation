package io.swagger.IT.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.swagger.model.NewTransaction;
import io.swagger.model.Transaction;
import io.swagger.model.dto.LoginDTO;
import io.swagger.service.TransactionService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.junit.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

public class TransactionSteps {
    HttpHeaders headers = new HttpHeaders();
    String baseUrl = "http://localhost:8080/api/";
    String singleUrl = "Transactions";
    RestTemplate template = new RestTemplate();
    ResponseEntity<String> responseEntity;
    private String token = getToken();

    public TransactionSteps() throws JSONException, URISyntaxException {
    }

    public String getToken() throws URISyntaxException, JSONException {
        URI uri = new URI(baseUrl + "login");

        LoginDTO login = new LoginDTO("JD0001", "Wachtwoord1#");
        HttpEntity<LoginDTO> entity = new HttpEntity<>(login, headers);

        responseEntity = template.postForEntity(uri, entity, String.class);
        return responseEntity.getBody();
    }

    @When("I retrieve all transactions")
    public void iRetrieveAllTransactions() throws URISyntaxException {
        URI uri = new URI(baseUrl + "Customers/" + singleUrl);
        headers.add("Authorization", token);
        Transaction transaction = new Transaction();
        HttpEntity<Transaction> entity = new HttpEntity(transaction, headers);
        responseEntity = template.exchange(uri, HttpMethod.GET, entity, String.class);
    }
    @Then("I get http status {int}")
    public void iGetHttpStatus(int status) {
        Assert.assertEquals(status, responseEntity.getStatusCodeValue());
    }

    @When("I post new transaction")
    public void iPostNewTransaction() throws URISyntaxException {
        URI uri = new URI(baseUrl + singleUrl);
        headers.add("Authorization", token);
        NewTransaction transaction = new NewTransaction(1.00, LocalDateTime.now(), "NL01INHO0000000002", "NL01INHO0000000003", 2l);
        HttpEntity<Transaction> entity = new HttpEntity(transaction, headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
    }

    @When("I post new transaction over transaction limit")
    public void iPostNewTransactionOverTransactionLimit() throws URISyntaxException {
        URI uri = new URI(baseUrl + singleUrl);
        headers.add("Authorization", token);
        NewTransaction transaction = new NewTransaction(200000.00, LocalDateTime.now(), "NL01INHO0000000002", "NL01INHO0000000003", 2l);
        HttpEntity<Transaction> entity = new HttpEntity(transaction, headers);
        try{
            responseEntity = template.postForEntity(uri, entity, String.class);
        }catch (HttpClientErrorException ex){
            responseEntity = new ResponseEntity<String>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @When("I post null transaction")
    public void iPostNullTransaction() throws URISyntaxException {
        URI uri = new URI(baseUrl + singleUrl);
        headers.add("Authorization", token);
        NewTransaction transaction = new NewTransaction();
        HttpEntity<Transaction> entity = new HttpEntity(transaction, headers);
        try{
            responseEntity = template.postForEntity(uri, entity, String.class);
        }catch (HttpClientErrorException ex){
            responseEntity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @When("I post new transaction user doesnt exists")
    public void iPostNewTransactionUserDoesntExists() throws URISyntaxException {
        URI uri = new URI(baseUrl + singleUrl);
        headers.add("Authorization", token);
        NewTransaction transaction = new NewTransaction(1.00, LocalDateTime.now(), "NL01INHO0000000002", "NL01INHO0000000003", 99000l);
        HttpEntity<Transaction> entity = new HttpEntity(transaction, headers);
        try{
            responseEntity = template.postForEntity(uri, entity, String.class);
        }catch (HttpClientErrorException ex){
            responseEntity = new ResponseEntity<String>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @When("I post new transaction account from doesnt exists")
    public void iPostNewTransactionAccountFromDoesntExists() throws URISyntaxException {
        URI uri = new URI(baseUrl + singleUrl);
        headers.add("Authorization", token);
        NewTransaction transaction = new NewTransaction(1.00, LocalDateTime.now(), "aaa", "NL01INHO0000000003", 2l);
        HttpEntity<Transaction> entity = new HttpEntity(transaction, headers);
        try{
            responseEntity = template.postForEntity(uri, entity, String.class);
        }catch (HttpClientErrorException ex){
            responseEntity = new ResponseEntity<String>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @When("I post new transaction account to doesnt exists")
    public void iPostNewTransactionAccountToDoesntExists() throws URISyntaxException {
        URI uri = new URI(baseUrl + singleUrl);
        headers.add("Authorization", token);
        NewTransaction transaction = new NewTransaction(1.00, LocalDateTime.now(), "NL01INHO0000000002", "aaa", 2l);
        HttpEntity<Transaction> entity = new HttpEntity(transaction, headers);
        try{
            responseEntity = template.postForEntity(uri, entity, String.class);
        }catch (HttpClientErrorException ex){
            responseEntity = new ResponseEntity<String>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @When("I post new transaction account from is saving account and is not from user")
    public void iPostNewTransactionAccountFromIsSavingAccountAndIsNotFromUser() throws URISyntaxException {
        URI uri = new URI(baseUrl + singleUrl);
        headers.add("Authorization", token);
        NewTransaction transaction = new NewTransaction(1.00, LocalDateTime.now(), "NL01INHO0000000004", "NL01INHO0000000002", 3l);
        HttpEntity<Transaction> entity = new HttpEntity(transaction, headers);
        try{
            responseEntity = template.postForEntity(uri, entity, String.class);
        }catch (HttpClientErrorException ex){
            responseEntity = new ResponseEntity<String>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @When("I post new transaction account to is saving account and is not from user")
    public void iPostNewTransactionAccountToIsSavingAccountAndIsNotFromUser() throws URISyntaxException {
        URI uri = new URI(baseUrl + singleUrl);
        headers.add("Authorization", token);
        NewTransaction transaction = new NewTransaction(1.00, LocalDateTime.now(), "NL01INHO0000000002", "NL01INHO0000000004", 2l);
        HttpEntity<Transaction> entity = new HttpEntity(transaction, headers);
        try{
            responseEntity = template.postForEntity(uri, entity, String.class);
        }catch (HttpClientErrorException ex){
            responseEntity = new ResponseEntity<String>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @When("I post new transaction exceeds balance")
    public void iPostNewTransactionExceedsBalance() throws URISyntaxException {
        URI uri = new URI(baseUrl + singleUrl);
        headers.add("Authorization", token);
        NewTransaction transaction = new NewTransaction(100.00, LocalDateTime.now(), "NL01INHO0000000003", "NL01INHO0000000002", 2l);
        HttpEntity<Transaction> entity = new HttpEntity(transaction, headers);
        try{
            responseEntity = template.postForEntity(uri, entity, String.class);
        }catch (HttpClientErrorException ex){
            responseEntity = new ResponseEntity<String>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @When("I post new transaction exceeds day limit")
    public void iPostNewTransactionExceedsDayLimit() throws URISyntaxException {
        URI uri = new URI(baseUrl + singleUrl);
        headers.add("Authorization", token);
        NewTransaction transaction = new NewTransaction(1000.00, LocalDateTime.now(), "NL01INHO0000000002", "NL01INHO0000000003", 2l);
        HttpEntity<Transaction> entity = new HttpEntity(transaction, headers);
        try{
            responseEntity = template.postForEntity(uri, entity, String.class);
        }catch (HttpClientErrorException ex){
            responseEntity = new ResponseEntity<String>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}

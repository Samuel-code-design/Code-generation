package io.swagger.IT.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.swagger.model.dto.LoginDTO;
import io.swagger.model.dto.RegisterDTO;
import org.json.JSONException;
import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class RegisterSteps {

    private HttpHeaders headers = new HttpHeaders();
    private String baseUrl = "http://localhost:8080/api/register";
    private RestTemplate template = new RestTemplate();
    private ResponseEntity<String> responseEntity;

    @When("User registreert")
    public void userRegistreert() throws URISyntaxException, JsonProcessingException {
        RegisterDTO registerDTO = new RegisterDTO("test", "Wachtwoord1", "John", "Doe",
                "JohnDoe@gmail.com", "06 12345678");
        ObjectMapper mapper = new ObjectMapper();
        URI uri = new URI(baseUrl);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(registerDTO), headers);
        responseEntity = template.postForEntity(uri, entity , String.class);
    }

    @When("User registreert met username die al bestaat")
    public void userRegistreertMetUsernameDieAlBestaat() throws URISyntaxException, JsonProcessingException {
        RegisterDTO registerDTO = new RegisterDTO("JD0001", "Wachtwoord1", "John", "Doe",
                "JohnDoe@gmail.com", "06 12345678");
        ObjectMapper mapper = new ObjectMapper();
        URI uri = new URI(baseUrl);
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(registerDTO), headers);
            responseEntity = template.postForEntity(uri, entity , String.class);
        }catch (HttpStatusCodeException e) {
            responseEntity = ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsString());
        }
    }
    @When("User registreert met email die al bestaat")
    public void userRegistreertMetEmailDieAlBestaat() throws URISyntaxException, JsonProcessingException {
        RegisterDTO registerDTO = new RegisterDTO("test", "Wachtwoord1", "John", "Doe",
                "samuel11hoi@gmail.com", "06 12345678");
        ObjectMapper mapper = new ObjectMapper();
        URI uri = new URI(baseUrl);
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(registerDTO), headers);
            responseEntity = template.postForEntity(uri, entity , String.class);
        }catch (HttpStatusCodeException e) {
            responseEntity = ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsString());
        }
    }

    @When("User registreert met wachtwoord die te kort is")
    public void userRegistreertMetWachtwoordDieTeKortIs() throws URISyntaxException, JsonProcessingException {
        RegisterDTO registerDTO = new RegisterDTO("test", "1234", "John", "Doe",
                "JohnDoe@gmail.com", "06 12345678");
        ObjectMapper mapper = new ObjectMapper();
        URI uri = new URI(baseUrl);
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(registerDTO), headers);
            responseEntity = template.postForEntity(uri, entity , String.class);
        }catch (HttpStatusCodeException e) {
            responseEntity = ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsString());
        }
    }

    @Then("Is de status van het request {int}")
    public void isDeStatusVanHetRequest(int expected) {
        int response = responseEntity.getStatusCodeValue();
        Assert.assertEquals(expected, response);
    }



}

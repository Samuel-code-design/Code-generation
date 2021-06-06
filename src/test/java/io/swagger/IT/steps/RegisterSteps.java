package io.swagger.IT.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.swagger.model.dto.LoginDTO;
import io.swagger.model.dto.RegisterDTO;
import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class RegisterSteps {

    private HttpHeaders headers = new HttpHeaders();
    private String baseUrl = "https://localhost:8080/api/register/";
    private RestTemplate template = new RestTemplate();
    private ResponseEntity<String> responseEntity;

    @When("Ik registreer")
    public void ikRegistreer() throws URISyntaxException, JsonProcessingException {
        RegisterDTO registerDTO = new RegisterDTO("JD0001", "Wachtwoord1", "John", "Doe",
                "JohnDoe@gmail.com", "06 12345678");
        ObjectMapper mapper = new ObjectMapper();
        URI uri = new URI(baseUrl);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(registerDTO), headers);
        responseEntity = template.postForEntity(uri, entity , String.class);
    }

    @Then("Is de status van het request {int}")
    public void isDeStatusVanHetRequest(int expected) {
        int response = responseEntity.getStatusCodeValue();
        Assert.assertEquals(expected, response);
    }


}

package io.swagger.IT.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.swagger.model.dto.LoginDTO;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class LoginSteps {

    private HttpHeaders headers = new HttpHeaders();
    private String baseUrl = "https://localhost:8080/api/login/";
    private RestTemplate template = new RestTemplate();
    private ResponseEntity<String> responseEntity;

    @When("Ik inlog")
    public void ikInlog() throws URISyntaxException, JsonProcessingException {
        LoginDTO loginDTO = new LoginDTO("JD0001", "Wachtwoord1#");
        ObjectMapper mapper = new ObjectMapper();
        URI uri = new URI(baseUrl);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(loginDTO), headers);
        responseEntity = template.postForEntity(uri, entity , String.class);
    }


}

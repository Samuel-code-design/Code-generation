package io.swagger.IT.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpHeaders;
import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class TransactionSteps {
    HttpHeaders headers = new HttpHeaders();
    String baseUrl = "https://localhost:8080/api/swagger-ui/";

    RestTemplate template = new RestTemplate();

    ResponseEntity<String> responseEntity;

    @When("ik alle transactions ophaal")
    public void ikAlleUsersOphaal() throws URISyntaxException {
        URI uri = new URI(baseUrl+ "transactions");
        HttpEntity<String> entity = new HttpEntity(null, headers);
        responseEntity = template.getForEntity(uri, String.class);
    }
    @Then("I get http status {int}")
    public void iGetHttpStatus(int status) {
        Assert.assertEquals(responseEntity.getStatusCodeValue(), status);
    }
}

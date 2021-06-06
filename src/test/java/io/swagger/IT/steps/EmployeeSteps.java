package io.swagger.IT.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(properties = {"security.basic.enabled=false"})
@AutoConfigureMockMvc(addFilters = false)
public class EmployeeSteps {

    private HttpHeaders headers = new HttpHeaders();
    private String baseUrl = "https://localhost:8080/api/swagger-ui/";
    private RestTemplate template = new RestTemplate();
    private ResponseEntity<String> responseEntity;

    @When("i retrieve all users")
    public void iRetrieveAllUsers() throws URISyntaxException {
        URI uri = new URI(baseUrl + "users");
        HttpEntity<String> entity = new HttpEntity(null, headers);
        responseEntity = template.getForEntity(uri, String.class);
    }

    @When("i retrieve a user with id {int}")
    public void iRetrieveAUserWithId(int id) throws URISyntaxException {
        URI uri = new URI(baseUrl + "users/" + id);
        HttpEntity<String> entity = new HttpEntity(null, headers);
        responseEntity = template.getForEntity(uri, String.class);
    }

    @Then("is the status of the request {int}")
    public void isTheStatusOfTheRequest(int status) {
        int response = responseEntity.getStatusCodeValue();
        Assert.assertEquals(status, response);
    }

    //TODO: long?
    @Then("i get a list of {int} users")
    public void iGetAListOfUsers(int expectedSize) throws JSONException {
        String response = responseEntity.getBody();
        JSONArray array = new JSONArray(response);
        int actualSize = array.length();
        Assert.assertEquals(actualSize, expectedSize);
    }

    @Then("is de username {string}")
    public void isDeUsername(String username) throws JSONException {
        String response = responseEntity.getBody();
        JSONObject object = new JSONObject(response);
        String actualUsername = object.getString("username");
        Assert.assertEquals(actualUsername, username);
    }
}

package com.zhacky.app.recime;

import com.zhacky.app.recime.domain.TrendingRecipe;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class CucumberSteps {
    @LocalServerPort
    String port;

    ResponseEntity<TrendingRecipe[]> lastResponse;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0");

    @Autowired
    TestRestTemplate restTemplate;

    @When("^the client calls \\/api\\/recipes$")
    public void theClientCallsApiRecipes() {
        String HOST = "http://localhost:";
        lastResponse = restTemplate.exchange(HOST + port + "/api/recipes", HttpMethod.GET, null, TrendingRecipe[].class);
    }

    @Then("the client receives status code of {int}")
    public void the_client_receives_status_code_of(int statusCode) throws  Throwable {
        assertThat("status code is " + statusCode,
                lastResponse.getStatusCodeValue() == statusCode);
    }
}

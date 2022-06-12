package steps;

import static io.restassured.RestAssured.given;
import static resources.Utils.getGlobalValue;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import models.University;
import org.junit.Assert;
import resources.Endpoints;
import resources.HttpMethod;
import resources.Utils;

import java.io.IOException;
import java.util.ArrayList;


public class SearchSteps extends Utils {

    RequestSpecification req;
    Response response;
    JsonArray resArray;
    private int resSize;
    ArrayList<University> payload = new ArrayList<>();




    @Given("User wants to list all universities.")
    public void setUp() throws IOException {

        req = given().spec(requestSpecification());

    }

    @When("Query param is {string} and value is {string}.")
    public void setQueryPram(String queryPram, String value) {

        req.queryParam(queryPram,value);

    }


    @When("API endpoint is {string} and Http Method is {string}")
    public void setEndpointAndMethod(String apiResource, String httpMethod){
        Endpoints resource = Endpoints.valueOf(apiResource);
        HttpMethod method = HttpMethod.valueOf(httpMethod);

        switch (method) {
            case POST:

                response = req.when().post(resource.getResource());
                break;

            case GET:
                response = req.when().get(resource.getResource());
                break;

            case PUT:
                response = req.when().put(resource.getResource());
                break;

            case DELETE:
                response = req.when().delete(resource.getResource());
                break;

            default:
                System.out.println("ERROR, METHOD NOT SUPPORTED");
                break;
        }


    }


    @Then("API status code should be {int}.")
    public void verifyResponseCode(int statusCode){
        response.then().assertThat().statusCode(statusCode);
    }

    @And("Response should not be empty.")
    public void responseShouldNotBeEmpty() {


        Assert.assertNotNull(response.getBody());
    }

    @And("Value in response {string} should equal {string}")
    public void extractAndVerifyResponse(String key, String value) {


        JsonArray responseAsArray =  response.getBody().as(JsonArray.class);

        for(int i=0; i<= responseAsArray.size()-1; i++){

                   JsonObject university = responseAsArray.get(i).getAsJsonObject();
                   Assert.assertEquals(university.get(key).getAsString(),value);
        }

    }

    @And("Value in response {string} should contains {string}")
    public void extractAndVerify(String key, String value) {


        JsonArray responseAsArray =  response.getBody().as(JsonArray.class);

        for(int i=0; i<= responseAsArray.size()-1; i++){

            JsonObject university = responseAsArray.get(i).getAsJsonObject();
            Assert.assertTrue(university.get(key).getAsString().contains(value));
        }

    }

    @And("Response should be empty.")
    public void responseShouldBeEmpty() {

        JsonArray responseAsArray = response.getBody().as(JsonArray.class);
        Assert.assertTrue(responseAsArray.isEmpty());
    }
}

package stepDefinitions;

import files.ReusableMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

public class StepDefinition extends Utils {

    ReusableMethods configReader = new ReusableMethods();
    RequestSpecification res;
    ResponseSpecification responseAddPlace;
    Response response;
    TestDataBuild data = new TestDataBuild();

    @Given("Add Place Payload with {string}, {string}, {string}")
    public void add_place_payload(String name, String language, String address) throws IOException {
        
        res = given().log().all().spec(requestSpecification())
            .body(data.addPlacePayload(name, language,address ));
    }

    @When("user calls {string} API with Post http request")
    public void user_calls_api_with_post_http_request(String string) {

        responseAddPlace = new ResponseSpecBuilder().expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

        response = res.when().post("maps/api/place/add/json")
            .then().log().all().spec(responseAddPlace).extract().response();
    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(int status) {

        assertEquals(status, response.getStatusCode());
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String value) {

        String textTocompare = configReader.rawToString(response.asString(), keyValue);
        assertEquals(value, textTocompare);
    }
}

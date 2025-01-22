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
import resources.APIResources;
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
    static String place_id;

    @Given("Add Place Payload with {string}, {string}, {string}")
    public void add_place_payload(String name, String language, String address) throws IOException {
        res = given().log().all().spec(requestSpecification())
                .body(data.addPlacePayload(name, language, address));
    }

    @When("user calls {string} API with {string} http request")
    public void user_calls_api_with_post_http_request(String resource, String httpMethod) {
        APIResources resources = APIResources.valueOf(resource);
        responseAddPlace = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
        switch (httpMethod.toUpperCase()) {
            case "POST":
                response = res.when().post(resources.getResource())
                        .then().log().all().spec(responseAddPlace).extract().response();
                break;
            case "GET":
                response = res.when().get(resources.getResource())
                        .then().log().all().spec(responseAddPlace).extract().response();
                break;
            case "DELETE":
                response = res.when().delete(resources.getResource())
                        .then().log().all().spec(responseAddPlace).extract().response();
                break;
            default:
                System.out.println(String.format("%http is not a valid http method", httpMethod));
                break;
        }
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

    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_placeId(String keyValue, String value) throws IOException {
        place_id = configReader.rawToString(response.asString(), "place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", place_id);
        user_calls_api_with_post_http_request(value, "Get");
        assertEquals(keyValue, configReader.rawToString(response.asString(), "name"));
    }

    @Then("DeletePlaceAPI Payload")
    public void deletePlacePayload() throws IOException {
        res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }
}

package stepDefinitions;

import files.ReusableMethods;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojoClasses.AddPlace;
import pojoClasses.Location;
import java.util.ArrayList;
import java.util.List;

public class StepDefinition {
    ReusableMethods configReader = new ReusableMethods("");

    @Given("Add Place Payload")
    public void add_place_payload() {
         AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress("40, inner layout, cohe 10");
        addPlace.setLanguage("English-EN");
        addPlace.setPhone_number("(+92)983 893 3837)");
        addPlace.setWebsite("http://google.com");
        addPlace.setName("Allen Walker");
        List<String> typesList = new ArrayList<String>();
        typesList.add("foot park");
        typesList.add("gun shop");
        addPlace.setTypes(typesList);
        Location location = new Location();
        location.setLat(-38.375494);
        location.setLng(33.42362);
        addPlace.setLocation(location);

        RequestSpecification requestAddPlace = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
            .addQueryParam("key", "qaclick123")
            .setContentType(ContentType.JSON).build();
    }

    @When("user calls {string} API with Post http request")
    public void user_calls_api_with_post_http_request(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}

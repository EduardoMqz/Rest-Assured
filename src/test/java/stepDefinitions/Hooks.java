package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        StepDefinition stepDefinition = new StepDefinition();
        if (StepDefinition.place_id == null) {
            stepDefinition.add_place_payload("Shetty", "French", "Asia");
            stepDefinition.user_calls_api_with_post_http_request("AddPlaceAPI", "Post");
            stepDefinition.verify_placeId("Shetty", "getPlaceAPI");
        }
    }
}

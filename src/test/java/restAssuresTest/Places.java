package restAssuresTest;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import files.ReusableMethods;
import io.restassured.RestAssured;

public class Places {

    @Test
    public void addPlace() throws IOException{
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        ReusableMethods configReader = new ReusableMethods("");

        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
        .body(new String (Files.readAllBytes(Paths.get("src\\main\\resources\\addPlace.json"))))
        .when().post("maps/api/place/add/json")
        .then().log().all().assertThat().statusCode(200)
        .body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract().response()
        .asString();

          String placeId = configReader.rawToString(response, "place_id");

    }
}

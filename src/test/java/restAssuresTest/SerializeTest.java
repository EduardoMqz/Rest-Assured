package restAssuresTest;

import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojoClasses.AddPlace;
import pojoClasses.Location;

public class SerializeTest {

    @Test
    public void serializeAddPlace(){
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress("40, inner layout, cohe 10");
        addPlace.setLanguage("English-EN");
        addPlace.setPhone_number("(+92)983 893 3837)");
        addPlace.setWebsite("http://google.com");
        addPlace.setName("Allen Walker");
        List<String> typesList = new ArrayList<String>();
        typesList.add("foot park");
        typesList.add("sword shop");
        addPlace.setTypes(typesList);
        Location location = new Location();
        location.setLat(-38.375494);
        location.setLng(33.42362);
        addPlace.setLocation(location);

        Response respo = given().log().all().queryParam("key", "qaclick123").body(addPlace)
        .when().post("maps/api/place/add/json")
        .then().log().all().assertThat().statusCode(200).extract().response();

        String reString = respo.asString();
        System.out.println(reString);

    }

}

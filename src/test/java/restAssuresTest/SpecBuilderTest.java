package restAssuresTest;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoClasses.AddPlace;
import pojoClasses.Location;

public class SpecBuilderTest {

    @Test
    public void serializeAddPlace() {
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

        ResponseSpecification responseAddPlace = new ResponseSpecBuilder().expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

        given().log().all().spec(requestAddPlace)
            .body(addPlace)
            .when().post("maps/api/place/add/json")
            .then().log().all().spec(responseAddPlace).extract().response();

    }
}

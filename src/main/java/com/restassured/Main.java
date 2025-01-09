package com.restassured;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import files.ReusableMethods;
import files.payload;

public class Main {
    public static void main(String[] args) {
        ReusableMethods configReader = new ReusableMethods("");
        // Validate if Add Place is working as expected
        // given - All input details
        // when - Submit API, resource, HTTP method
        // there - Validate response
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        String response = addPlace();

        // obtain place_id from responses body
        String placeId = configReader.rawToString(response, "place_id");// for parsing Json

        // update place with new address
        String newAddress = "69 Winter Walk, USA";
        updatePlace(placeId, newAddress);

        // Get place to validate if new address is present in response
        String getPlaceResponse = getPlace(placeId, newAddress);

        String addressResponse = configReader.rawToString(getPlaceResponse, "address");
        Assert.assertEquals(addressResponse, newAddress);

    }

    public static String addPlace(){
        return given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
        .body(payload.addPlace())
        .when().post("maps/api/place/add/json")
        .then().log().all().assertThat().statusCode(200)
        .body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract().response()
        .asString();
    }

    public static void updatePlace(String placeId, String newAddress){
        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
        .body(payload.updatePlace(placeId, newAddress))
        .when().put("maps/api/place/update/json")
        .then().log().all().assertThat().statusCode(200)
        .body("msg", equalTo("Address successfully updated"));
    }

    public static String getPlace(String placeId, String newAddress){
        return given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
        .when().get("maps/api/place/get/json")
        .then().log().all().assertThat().statusCode(200).body("address", equalTo(newAddress)).extract()
        .asString();
    }
}
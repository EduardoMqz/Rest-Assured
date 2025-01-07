package com.restassured;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReusableMethods;
import files.payload;

public class Main {
    public static void main(String[] args) {
        // Validate if Add Place is working as expected
        // given - All input details
        // when - Submit API, resource, HTTP method
        // there - Validate response
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
            .body(payload.addPlace())
            .when().post("maps/api/place/add/json")
            .then().log().all().assertThat().statusCode(200)
            .body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract().response()
            .asString();

        // obtain place_id from responses body
        String placeId = ReusableMethods.rawToString(response, "place_id");// for parsing Json

        // update place with new address
        String newAddress = "69 Winter Walk, USA";
        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
            .body(payload.updatePlace(placeId, newAddress))
            .when().put("maps/api/place/update/json")
            .then().log().all().assertThat().statusCode(200)
            .body("msg", equalTo("Address successfully updated"));

        // Get place to validate if new address is present in response
        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
            .when().get("maps/api/place/get/json")
            .then().log().all().assertThat().statusCode(200).body("address", equalTo(newAddress)).extract()
            .asString();

        String addressResponse = ReusableMethods.rawToString(getPlaceResponse, "address");
        Assert.assertEquals(addressResponse, newAddress);

    }
}
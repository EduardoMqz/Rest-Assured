package com.restassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

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
        JsonPath jPath = new JsonPath(response);// for parsing Json
        String placeId = jPath.getString("place_id");

        // update place with new address
        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
        .body(payload.updatePlace(placeId))
        .when().put("maps/api/place/update/json")
        .then().log().all().assertThat().statusCode(200)
        .body("msg", equalTo("Address successfully updated"));

        //Get place to validate if new address is present in response
        given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).header("Content-Type", "application/json")
        .when().get("maps/api/place/get/json")
        .then().log().all().assertThat().statusCode(200).body("address", equalTo("69 Winter Walk, USA"));
    }
}
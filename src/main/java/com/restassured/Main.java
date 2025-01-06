package com.restassured;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class Main {
    public static void main(String[] args) {
        // Validate if Add Place is working as expected
        // given - All input details
        // when - Submit API, resource, HTTP method
        // there - Validate response
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
            .body("{\r\n" + //
                                "    \"location\": {\r\n" + //
                                "        \"lat\": -38.383494,\r\n" + //
                                "        \"lng\": 33.427362\r\n" + //
                                "    },\r\n" + //
                                "    \"accuracy\": 50,\r\n" + //
                                "    \"name\": \"Frontierline park\",\r\n" + //
                                "    \"phone_number\": \"(+92)983 893 3837\",\r\n" + //
                                "    \"address\": \"30, side layout, cohen 10\",\r\n" + //
                                "    \"types\": [\r\n" + //
                                "        \"shoe park\",\r\n" + //
                                "        \"gundam shop\"\r\n" + //
                                "    ],\r\n" + //
                                "    \"website\": \"http://google.com\",\r\n" + //
                                "    \"language\": \"French-IN\"\r\n" + //
                                "}")
            .when().post("maps/api/place/add/json")
            .then().log().all().assertThat().statusCode(200);
    }
}
package restAssuresTest;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import files.ReusableMethods;
import io.restassured.RestAssured;

public class Oauth2 {

    @Test
    public void accessEndPoint(){
        ReusableMethods configReader = new ReusableMethods("oAuth2Api");
        RestAssured.baseURI = configReader.get("baseURL");

        String response = given()
        .formParam("client_id", configReader.get("client_id"))
        .formParam("client_secret", configReader.get("client_secret"))
        .formParam("grant_type", configReader.get("grant_type"))
        .formParam("scope", "trust")
        .when().log().all()
        .post(configReader.get("resource")).asString();

        String accessToken = configReader.rawToString(response,"access_token");

        //getCourseDetails
        response = given()
        .queryParam("access_token", accessToken)
        .when().log().all()
        .get(configReader.get("getCourseDetails")).asString();

        System.out.println(response);


    }

}

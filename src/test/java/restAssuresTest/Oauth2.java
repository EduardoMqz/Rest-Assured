package restAssuresTest;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.ReusableMethods;
import io.restassured.RestAssured;
import pojoClasses.API;
import pojoClasses.GetCourse;
import pojoClasses.WebAutomation;

public class Oauth2 {

    @Test
    public void accessEndPoint() {
        ReusableMethods configReader = new ReusableMethods("oAuth2Api");
        RestAssured.baseURI = configReader.get("baseURL");

        String response = given()
                .formParam("client_id", configReader.get("client_id"))
                .formParam("client_secret", configReader.get("client_secret"))
                .formParam("grant_type", configReader.get("grant_type"))
                .formParam("scope", "trust")
                .when().log().all()
                .post(configReader.get("resource")).asString();

        String accessToken = configReader.rawToString(response, "access_token");

        // getCourseDetails
        GetCourse getCourse = given()
                .queryParam("access_token", accessToken)
                .when().log().all()
                .get(configReader.get("getCourseDetails")).as(GetCourse.class);

        System.out.println(getCourse.getLinkedIn());
        System.out.println(getCourse.getInstructor());
        System.out.println(getCourse.getCourses().getApi().get(1).getCourseTitle());


        Map<String, API> courseMap = new HashMap<>();
        for (API apiCourse : getCourse.getCourses().getApi()) {
            courseMap.put(apiCourse.getCourseTitle(), apiCourse);
        }

        API matchingCourse = courseMap.get("SoapUI Webservices testing");
        if (matchingCourse != null) {
            System.out.println("Price of the course: " + matchingCourse.getPrice());
        }

        //get course name of weAutomation
        Map<String, WebAutomation> courseMapWebAuto = new HashMap<>();
        String[] courseTitile = {"Selenium Webdriver Java","Cypress","Protractor"};
        ArrayList<String> realList = new ArrayList<String>();
        for (WebAutomation webAutomationCourse : getCourse.getCourses().getWebAutomation()) {
            courseMapWebAuto.put(webAutomationCourse.getCourseTitle(), webAutomationCourse);         
            realList.add(webAutomationCourse.getCourseTitle());
        }
        List<String> expectedList = Arrays.asList(courseTitile);
        Assert.assertTrue(realList.equals(expectedList));
    }
}

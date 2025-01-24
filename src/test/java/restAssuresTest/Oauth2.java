package restAssuresTest;

import static io.restassured.RestAssured.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        // get course name of weAutomation
        Map<String, WebAutomation> courseMapWebAuto = new HashMap<>();
        String[] courseTitile = { "Selenium Webdriver Java", "Cypress", "Protractor" };
        ArrayList<String> realList = new ArrayList<String>();
        for (WebAutomation webAutomationCourse : getCourse.getCourses().getWebAutomation()) {
            courseMapWebAuto.put(webAutomationCourse.getCourseTitle(), webAutomationCourse);
            realList.add(webAutomationCourse.getCourseTitle());
        }
        List<String> expectedList = Arrays.asList(courseTitile);
        Assert.assertTrue(realList.equals(expectedList));
    }

    @Test
    public void googleLogin() throws InterruptedException {
        ReusableMethods configReader = new ReusableMethods("OAuth");
        WebDriver driver = new ChromeDriver();
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));

        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        
        driver.get(configReader.get("google_url"));
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(configReader.get("email"));
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(configReader.get("password"));
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        String code = driver.getCurrentUrl();
        code = code.split("code=")[1];
        code = code.split("&scope")[0];


        String accessToken = given().urlEncodingEnabled(false).queryParams("code", code)
                .queryParams("client_id", configReader.get("client_id"))
                .queryParams("client_secret", configReader.get("client_secret"))
                .queryParams("redirect_uri", configReader.get("getResource"))
                .queryParams("grant_type", configReader.get("grant_type"))
                .when().log().all().post(configReader.get("access_token_url")).asString();

        accessToken = configReader.rawToString(accessToken, "access_token");

        String response = given().queryParam("access_token", accessToken)
                .when().log().all().get(configReader.get("getResource")).asString();
                System.out.println(response);

    }
}

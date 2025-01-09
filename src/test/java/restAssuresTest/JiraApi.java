package restAssuresTest;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;
import files.ReusableMethods;
import io.restassured.RestAssured;

public class JiraApi {

    @Test
    public void createIssue() throws IOException {
        ReusableMethods configReader = new ReusableMethods("jiraApi");
        RestAssured.baseURI = configReader.get("baseURL");
        

        String createIssueResponse = given().header( "Content-Type", "application/json")
        .header("Authorization", "Basic "+configReader.get("token64"))
        .body(new String (Files.readAllBytes(Paths.get("src\\main\\resources\\jiraIssue.json")))).log().all()
        .post(configReader.get("createIssue"))
        .then().log().all().assertThat().statusCode(201).extract().asString();

        String idIssue = configReader.rawToString(createIssueResponse, "id");

        //add attachment
        given().header("X-Atlassian-Token","no-check").pathParam("id", idIssue)
        .header("Authorization", "Basic "+configReader.get("token64"))
        .multiPart("file", new File("src\\main\\resources\\attachment.jpg")).log().all()
        .post(configReader.get("addAttachment"))
        .then().log().all().assertThat().statusCode(200);
    }
}

package restAssuresTest;

import org.testng.annotations.Test;
import files.ReusableMethods;
import io.restassured.RestAssured;

public class JiraApi {

    @Test
    public void createIssue() {
        RestAssured.baseURI = ReusableMethods.get("baseURL");
    }

}

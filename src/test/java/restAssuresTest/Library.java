package restAssuresTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import java.io.IOException;
import java.util.HashMap;

public class Library {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle) {
        // Add book
        ReusableMethods configReader = new ReusableMethods("");
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().header("Content-Type", "application/json").body(payload.addBook(isbn, aisle))
            .when().post("Library/Addbook.php")
            .then().assertThat().statusCode(200).extract().response().asString();
        String responseId = configReader.rawToString(response, "ID");
        System.out.println("ID book: " + responseId);
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData() {
        return new Object[][] { { "eshf", "478" }, { "esfr", "459" }, { "serf", "584" }, { "sefr", "585" },
            { "rsfe", "457" } };
    }

    @Test
    public void excelDriven() throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "RestAssured");
        map.put("isbn", "gdvtres");
        map.put("aisle", "8526");
        map.put("author", "Rahul");
        HashMap<String, Object> maplocation = new HashMap<>();
        maplocation.put("lat", "1245");
        maplocation.put("lng", "5421");
        map.put("location", maplocation);
        ReusableMethods configReader = new ReusableMethods("excelDrivenLibrary");
        RestAssured.baseURI = configReader.get("baseURI");
        String response = given().header("Content-Type", "application/json")
            .body(map)
            .when().post(configReader.get("libraryResource"))
            .then().assertThat().statusCode(200).extract().response().asString();
        String x = configReader.rawToString(response, "ID");

    }

}

package restAssuresTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class Library {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle) {
        // Add book
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().header("Content-Type", "application/json").body(payload.addBook(isbn, aisle))
                .when().post("Library/Addbook.php")
                .then().assertThat().statusCode(200).extract().response().asString();
        String responseId = ReusableMethods.rawToString(response, "ID");
        System.out.println("ID book: " + responseId);
    }

    @DataProvider(name="BooksData")
    public Object[][] getData() {
        return new Object[][] {{"eshf","478"}, {"esfr","459"}, {"serf","584"}, {"sefr","585"}, {"rsfe","457"}};
    }

}

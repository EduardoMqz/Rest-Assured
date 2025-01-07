package files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
    public static String rawToString(String response, String stringToGet){
        JsonPath jsonPath = new JsonPath(response);
        String stringResponse = jsonPath.getString(stringToGet);
        return stringResponse;
    }

}

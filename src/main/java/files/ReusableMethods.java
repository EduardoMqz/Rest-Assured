package files;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;


@SuppressWarnings("unchecked")
public class ReusableMethods {

    private static Map<String, String> config;

    public ReusableMethods(String name){
        ObjectMapper mapper = new ObjectMapper();
        try {
            config = mapper.readValue(new File("src\\main\\resources\\"+name+".json"), Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config", e);
        } 
    }

    public String get(String key) {
        return config.get(key);
    }
    
    public String rawToString(String response, String stringToGet){
        JsonPath jsonPath = new JsonPath(response);
        String stringResponse = jsonPath.getString(stringToGet);
        return stringResponse;
    }

}

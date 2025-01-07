package files;

public class payload {

    public static String addPlace() {
        return "{\r\n" + //
                "    \"location\": {\r\n" + //
                "        \"lat\": -38.383494,\r\n" + //
                "        \"lng\": 33.427362\r\n" + //
                "    },\r\n" + //
                "    \"accuracy\": 50,\r\n" + //
                "    \"name\": \"Frontierline park\",\r\n" + //
                "    \"phone_number\": \"(+92)983 893 3837\",\r\n" + //
                "    \"address\": \"40, side layout, cohen 20\",\r\n" + //
                "    \"types\": [\r\n" + //
                "        \"shoe park\",\r\n" + //
                "        \"gundam shop\"\r\n" + //
                "    ],\r\n" + //
                "    \"website\": \"http://google.com\",\r\n" + //
                "    \"language\": \"French-IN\"\r\n" + //
                "}";
    }

    public static String updatePlace(String placeId, String newAddress){
        return "{\r\n" + //
                        "    \"place_id\": \""+placeId+"\",\r\n" + //
                        "    \"address\": \""+newAddress+"\",\r\n" + //
                        "    \"key\": \"qaclick123\"\r\n" + //
                        "}";
    }

    public static String coursePrice(){
        return "{\n" + //
                        "  \"dashboard\": {\n" + //
                        "    \"purchaseAmount\": 1162,\n" + //
                        "    \"website\": \"rahulshettyacademy.com\"\n" + //
                        "  },\n" + //
                        "  \"courses\": [\n" + //
                        "    {\n" + //
                        "      \"title\": \"Selenium Python\",\n" + //
                        "      \"price\": 50,\n" + //
                        "      \"copies\": 6\n" + //
                        "    },\n" + //
                        "    {\n" + //
                        "      \"title\": \"Cypress\",\n" + //
                        "      \"price\": 40,\n" + //
                        "      \"copies\": 4\n" + //
                        "    },\n" + //
                        "    {\n" + //
                        "      \"title\": \"RPA\",\n" + //
                        "      \"price\": 45,\n" + //
                        "      \"copies\": 10\n" + //
                        "    },\n" + //
                        "    {\n" + //
                        "      \"title\": \"Appium\",\n" + //
                        "      \"price\": 36,\n" + //
                        "      \"copies\": 7\n" + //
                        "    }\n" + //
                        "  ]\n" + //
                        "}";
   }

}

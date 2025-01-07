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
        return "{\r\n" + //
                        "\"dashboard\": {\r\n" + //
                        "\"purchaseAmount\": 910,\r\n" + //
                        "\"website\": \"rahulshettyacademy.com\"\r\n" + //
                        "},\r\n" + //
                        "\"courses\": [\r\n" + //
                        "{\r\n" + //
                        "\"title\": \"Selenium Python\",\r\n" + //
                        "\"price\": 50,\r\n" + //
                        "\"copies\": 6\r\n" + //
                        "},\r\n" + //
                        "{\r\n" + //
                        "\"title\": \"Cypress\",\r\n" + //
                        "\"price\": 40,\r\n" + //
                        "\"copies\": 4\r\n" + //
                        "},\r\n" + //
                        "{\r\n" + //
                        "\"title\": \"RPA\",\r\n" + //
                        "\"price\": 45,\r\n" + //
                        "\"copies\": 10\r\n" + //
                        "}\r\n" + //
                        "]\r\n" + //
                        "}";
   }

}

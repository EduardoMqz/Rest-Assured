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
                "    \"address\": \"36, side layout, cohen 16\",\r\n" + //
                "    \"types\": [\r\n" + //
                "        \"shoe park\",\r\n" + //
                "        \"gundam shop\"\r\n" + //
                "    ],\r\n" + //
                "    \"website\": \"http://google.com\",\r\n" + //
                "    \"language\": \"French-IN\"\r\n" + //
                "}";
    }

    public static String updatePlace(String placeId){
        return "{\r\n" + //
                        "    \"place_id\": \""+placeId+"\",\r\n" + //
                        "    \"address\": \"69 Winter Walk, USA\",\r\n" + //
                        "    \"key\": \"qaclick123\"\r\n" + //
                        "}";
    }

}

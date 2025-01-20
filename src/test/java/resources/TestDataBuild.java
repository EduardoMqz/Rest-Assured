package resources;

import java.util.ArrayList;
import java.util.List;

import pojoClasses.AddPlace;
import pojoClasses.Location;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name, String language, String address){

        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress(address);
        addPlace.setLanguage(language);
        addPlace.setPhone_number("(+92)983 893 3837)");
        addPlace.setWebsite("http://google.com");
        addPlace.setName(name);

        List<String> typesList = new ArrayList<String>();
        typesList.add("foot park");
        typesList.add("gun shop");

        Location location = new Location();
        location.setLat(-38.375494);
        location.setLng(33.42362);

        addPlace.setLocation(location);
        addPlace.setTypes(typesList);

        return addPlace;
    }

    public String deletePlacePayload(String placeId){
        return "{\r\n  \"place_id\":\""+placeId+"\"\r\n}";
    }

}

package resources;

import java.util.ArrayList;
import java.util.List;

import pojoClasses.AddPlace;
import pojoClasses.Location;

public class TestDataBuild {

    public AddPlace addPlacePayload(){

        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress("40, inner layout, cohe 10");
        addPlace.setLanguage("English-EN");
        addPlace.setPhone_number("(+92)983 893 3837)");
        addPlace.setWebsite("http://google.com");
        addPlace.setName("Allen Walker");

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

}

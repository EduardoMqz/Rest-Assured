package resources;

//enum is a special claass in java which has collection of constants or methods
public enum APIResources {

    AddPlaceAPI("maps/api/place/add/json"),
    getPlaceAPI("maps/api/place/get/json"),
    deletePlaceAPI("maps/api/place/delete/json");
    private String resource;

    APIResources(String resources){
        this.resource = resources;
    }

    public String getResource(){
        return resource;
    }
    
        

}

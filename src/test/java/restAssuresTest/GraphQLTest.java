package restAssuresTest;

import static io.restassured.RestAssured.given;

import org.junit.Test;

import files.ReusableMethods;

public class GraphQLTest {
    ReusableMethods configReader = new ReusableMethods();
    @Test
    public void getCharacter() {
        String response = given().log().all().header("Content-Type", "application/json")
            .body("{\"query\":\"query{\\n" +
                "  character(characterId: 650){\\n" +
                "    name\\n" +
                "    gender\\n" +
                "    status\\n" +
                "    id\\n" +
                "  }\\n" +
                "  \\n" +
                "}\",\"variables\":null}")
            .when().post("https://rahulshettyacademy.com/gq/graphql")
            .then().extract().response().asString();

        System.out.println(response);

        String name = configReader.rawToString(response, "data.character.name");
        System.out.println(name);
    }
    @Test
    public void createGraphQL(){
        String locationName = "Vongola Famiglia";
        String characterName = "Reborn";
        String episodeName = "Final";

        String response = given().log().all().header("Content-Type", "application/json")
            .body(
                                "{\"query\":\"mutation ($locationName: String!, $characterName: String!, $episodeName: String!) {\\n" + //
                                "  createLocation(location: {name: $locationName, type: \\\"Center\\\", dimension: \\\"245\\\"}) {\\n" + //
                                "    id\\n" + //
                                "  }\\n" + //
                                "  createCharacter(character: {name: $characterName, type: \\\"Teen\\\", status: \\\"Alive\\\", species: \\\"Exorcist\\\", gender: \\\"Male\\\", image: \\\"png\\\", originId: 17269, locationId: 17269}) {\\n" + //
                                "    id\\n" + //
                                "  }\\n" + //
                                "  createEpisode(episode: {name: $episodeName, air_date: \\\"01/08/1997\\\", episode: \\\"001\\\"}) {\\n" + //
                                "    id\\n" + //
                                "  }\\n" + //
                                "  deleteLocations(locationIds: [11765]) {\\n" + //
                                "    locationsDeleted\\n" + //
                                "  }\\n" + //
                                "}\\n" + //
                                "\",\"variables\":{\"locationName\":\""+locationName+"\",\"characterName\":\""+characterName+"\",\"episodeName\":\""+episodeName+"\"}}")
            .when().post("https://rahulshettyacademy.com/gq/graphql")
            .then().extract().response().asString();
            System.out.println(response);
            String name = configReader.rawToString(response, "data.createCharacter.id");
        System.out.println(name);
    }

}

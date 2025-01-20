Feature: Validating PLace Api's

  @AddPlace
  Scenario Outline: Verify if Place is beign succesfully added using AddPlace API
    Given Add Place Payload with "<name>", "<language>", "<address>"
    When user calls "AddPlaceAPI" API with "Post" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name         | language   | address            |
      | Allen Walker | English-EN | World Trade Center |
      | Daruma       | Japan-JP   | Meiji Castle       |

  @DeletePlace
  Scenario Outline: verify if delete place funcionality is working
    Given DeletePlaceAPI Payload
    When user calls "deletePlaceAPI" API with "Post" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"

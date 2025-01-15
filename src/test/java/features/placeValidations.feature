Feature: Validating PLace Api's

  Scenario: Verify if Place is beign succesfully added using AddPlace API
    Given Add Place Payload
    When user calls "AddPlace" API with Post http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
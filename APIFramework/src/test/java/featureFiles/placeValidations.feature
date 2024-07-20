Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if place is succesfully added using Add Place API
	Given the Add Place payload is created with "<name>" "<language>" and "<address>"
	 When the "AddPlaceAPI" is called with a "POST" http request
	 Then the API call is successful
	  And the "status" in the response body is "OK"
	  And the "scope" in the response body is "APP"
	  And verify that the "place_id" maps to "<name>" using "GetPlaceAPI"
	  
	 Examples:
	 		| name               | language | address           |
	 		| Jamie McAlpine     | English  | 2 Test Road       |
	 		| Napolean Bonaparte | French   | 14 Waterloo Place |
	
#AddPlace scenario must be run before this - handled by hook 		
@DeletePlace @jm
Scenario: Verify if place is succesfully deledeted using Delete Place API
	Given the Delete Place payload is created
	 When the "DeletePlaceAPI" is called with a "POST" http request
	 Then the API call is successful


	
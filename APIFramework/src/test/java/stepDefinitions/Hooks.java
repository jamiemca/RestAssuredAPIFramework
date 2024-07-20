package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeDeletePlace() throws IOException {
		
		StepDefinitions steps = new StepDefinitions();
		
		if (StepDefinitions.placeId == null) {
			
			steps.addPlacePayloadIsCreated("Jamie McAlpine", "English", "2 Test Road");
			steps.apiIsCalledWithHttpRequest("AddPlaceAPI", "Post");
			steps.verifyThatValueMapsCorrectly("place_id", "Jamie McAlpine", "GetPlaceAPI");
		}
	}

}

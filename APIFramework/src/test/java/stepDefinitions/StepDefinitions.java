package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitions extends Utils {
	

	ResponseSpecification responseSpec;
	RequestSpecification response;
	Response apiResponse;
	TestDataBuild data = new TestDataBuild();
	static String placeId;
	
	@Given("the Add Place payload is created with {string} {string} and {string}")
	public void addPlacePayloadIsCreated(String name, String language, String address) throws IOException {
		
		response = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
	}
	
	@Given("the Delete Place payload is created")
	public void deletePlacePayloadIsCreated() throws IOException {
		
		response = given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
	}

	@When("the {string} is called with a {string} http request")
	public void apiIsCalledWithHttpRequest(String apiName, String apiRequestType) {
		
		APIResources resourceAPI = APIResources.valueOf(apiName);
		
		responseSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		if (apiRequestType.equalsIgnoreCase("Post")) {
			
			apiResponse = response.when().post(resourceAPI.getResource());
			
		} else if (apiRequestType.equalsIgnoreCase("Get")) {
			
			apiResponse = response.when().get(resourceAPI.getResource());
			
		}
	}

	@Then("the API call is successful")
	public void apiCallIsSuccessful() {
		
		assertEquals("Status code does not equal 200. Status code is: " + apiResponse.getStatusCode(),
				apiResponse.getStatusCode(), 200);	
	}

	@Then("the {string} in the response body is {string}")
	public void matchExpectedKeyValueToActual(String key, String expectedValue) {

		assertEquals(key + " value should be " + expectedValue + " but is " + getJsonPath(apiResponse, key),
				getJsonPath(apiResponse, key), expectedValue);
	}
	
	@Then("verify that the {string} maps to {string} using {string}")
	public void verifyThatValueMapsCorrectly(String key, String expectedName, String apiName) throws IOException {
		
		String keyValue = getJsonPath(apiResponse, key);
		response = given().spec(requestSpecification()).queryParam(key, keyValue);
		
		apiIsCalledWithHttpRequest(apiName, "Get");
		
		String actualName = getJsonPath(apiResponse, "name");
		assertEquals("Name of " + key + " should be " + expectedName + " but is " + actualName,
				expectedName, actualName);
	}

}

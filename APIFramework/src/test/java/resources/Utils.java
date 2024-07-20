package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification requestSpec;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		if (requestSpec == null) {
			
			PrintStream log = new PrintStream(new FileOutputStream("log.txt"));
			
			requestSpec = new RequestSpecBuilder()
					.setBaseUri(getGlobalValue("baseUrl"))
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON)
					.build();
			return requestSpec;
			
		} else {
			
			return requestSpec;
		}
	}
	
	public String getGlobalValue(String key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") 
				+ "\\src\\test\\java\\properties\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		
		String stringResponse = response.asString();
		JsonPath jsonPath = new JsonPath(stringResponse);
		return jsonPath.get(key).toString();
	}

}

package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String language, String address) {
		
		List<String> typesList = new ArrayList<String>();
		typesList.add("shoe park");
		typesList.add("shop");
		
		Location location = new Location();
		location.setLat(-38.383490);
		location.setLng(31.32569);
		
		AddPlace place = new AddPlace();
		place.setAccuracy(50);
		place.setAddress(address);
		place.setLanguage(language);
		place.setPhone_number("(91) 983 893 3937");
		place.setWebsite("https://rahulshettyacademy.com");
		place.setName(name);
		place.setTypes(typesList);
		place.setLocation(location);
		
		return place;
	}
	
	public String deletePlacePayload(String placeId) {
		
		String placeIdPayload = "\"place_id\":\"" + placeId + "\"";
		return placeIdPayload;
	}

}

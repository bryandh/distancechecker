package controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import enums.ApiKeys;

public class DistanceCheckerController {
	
	public String getDistance(String origin, String destination){
		origin = origin.replace(" ", "%20");
		destination = destination.replace(" ", "%20");
		String apiKey = (String) ApiKeys.GoogleDistanceMatrix.getValue();
		String distanceText = "";
		
		try {
			// Execute the API call and handle the response
			JSONObject jsonObjectResponse = new JSONObject(IOUtils.toString(new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origin + "&destinations=" + destination + "&key=" + apiKey)));
			String responseStatus = jsonObjectResponse.getString("status");
			if(!responseStatus.equals("OK"))
				throw new Exception(responseStatus);
			
			// Fetch the rows
			JSONArray rows = jsonObjectResponse.getJSONArray("rows");
			// Fetch the first row
			JSONObject row = rows.getJSONObject(0);
			// Fetch the elements
			JSONArray elements = row.getJSONArray("elements");
			// Fetch the first element
			JSONObject element = elements.getJSONObject(0);
			
			// Get the status of the API response. 
			String elementStatus = element.getString("status");
			// Check if the status equals OK, else throw an exception with the status.
			// The exception returns the status.
			if(!elementStatus.equals("OK"))
				throw new Exception(elementStatus);
			
			// So when the status is OK, fetch the distance.
			JSONObject distance = (JSONObject) element.get("distance");
			// Fetch the distance text.
			distanceText = distance.getString("text");
		} catch (JSONException e) {
			e.printStackTrace();
			return e.getMessage();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		return "Distance from origin to destination: " + distanceText;
	}
};
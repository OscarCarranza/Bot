package bot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GoogleMaps {

   private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		GoogleMaps http = new GoogleMaps();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();


	}

	// HTTP GET request
	private void sendGet() throws Exception {
                String origin = "Tegucigalpa", destination = "La+Ceiba";
                String YOUR_API_KEY = "AIzaSyCm5bDkt7Ohgm4l5VhBstydD-fGSmcr6oo";
                String url = "https://maps.googleapis.com/maps/api/directions/json?origin="+
                              origin + "&destination=" + destination +"&key=" + YOUR_API_KEY;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}

    
}

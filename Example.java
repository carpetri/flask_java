import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import org.json.simple.JSONObject;


public class Example {

	// Sett the URLs from flask
	private static final String USER_AGENT = "Mozila/5.0";
	private static final String GET_URL = "http://localhost:5000/";
	private static final String POST_URL = "http://localhost:5000/avg";

	public static void main(String[] args) throws IOException {

		// Get the homepage
		sendGET();
		System.out.println("GET DONE\n");

		// Post to get the average example
		sendPOST();
		System.out.println("POST DONE");
	}

	private static void sendGET() throws IOException {
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("GET request not worked");
		}

	}

	private static void sendPOST() throws IOException {
		URL obj = new URL(POST_URL);
		JSONObject json = new JSONObject();
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setConnectTimeout(5000);
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setDoOutput(true);
        con.setDoInput(true);
       	con.setRequestMethod("POST");
    	OutputStream os = con.getOutputStream();

		// Simple list example
		List<Integer> list1 = new ArrayList<Integer>();
        for(int i = 0; i < 11; i++) {
            list1.add( i );
        }
		
		// Create a Json for the POST request.
		json.put("list", list1.toString() );

		//  Print for testing purposes
		System.out.println("JSON IS : " + json.toString() );

		// Send the json
		os.write(json.toString().getBytes("UTF-8"));
		os.close();

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == 201) { 
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println("The average is: " + response.toString());
		} else {
			System.out.println("POST request not working");
		}
	}

}
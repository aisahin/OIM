
package oim;

import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class Geocode {
    
    Helper helper = new Helper();
    
    static MetadataReader mdr = new MetadataReader();
    
    String  requestURL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=";
    
    private final static String API_KEY = "AIzaSyBunNYIlyb3IUxVDYXMyFpkxkpQutTQPqw";    
    private final String USER_AGENT = "Mozilla/5.0";
    
    public Geocode() {}
    
    public void GetGeocode(Map<String, Map<String, String>> itemData){
        
        itemData.forEach((outerKey, outerValue) 
                
                        -> outerValue.forEach((innerKey, innerValue) -> System.out.println("--- " + innerKey + " : " + innerValue)
                        
                                        )
                );
    }
    
    // HTTP GET request
    public String sendGet(double latitude, double longitude) throws  IOException, JSONException {

        JSONObject item = new JSONObject();
        
        String url = requestURL + latitude + "," + longitude + "&key=" + API_KEY;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()) );
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {

                response.append(inputLine);
        }
        
        in.close();
        item = new JSONObject(response.toString());
        
        String location = (String) item.getJSONArray("results").getJSONObject(0).getJSONArray("address_components").getJSONObject(4).opt("long_name");
        System.out.println(location);
        return location;
    }    
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;


public class GeoParser {

    private final String USER_AGENT = "Mozilla/5.0";
    private String BASE_URL = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private String apiKey;


    public GeoParser(String apiKey) {
        this.apiKey = apiKey;
    }

   //public methods for geocoding suggested bike racks
   public double getLatitude(String address) {
        JSONObject jsonObject = getGeoCodeJSON(address);
        return getLat(jsonObject);
   }

   public double getLongitude(String address) {
        JSONObject jsonObject = getGeoCodeJSON(address);
        return getLon(jsonObject);
   }


    //address can contain spaces
    public String makeRequestURL(String address) {
        String addressNoSpaces = address.replaceAll("\\s+", "+");
        return BASE_URL + addressNoSpaces + "&key=" + apiKey;
    }


    public JSONObject getGeoCodeJSON(String address) {

        try {
        String stringURL = makeRequestURL(address);

        URL url = new URL(stringURL);

        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
          String line;
            StringBuffer response = new StringBuffer();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

        reader.close();



        return new JSONObject(response.toString());
        } catch(Exception e) {
            System.out.println("problem getting the json object: " + e.toString());

            String errorString = "";
            for (StackTraceElement element : e.getStackTrace()) {
                errorString += element + "\n";
            }
            System.out.println(errorString);
        }

        return null;
    }



    public double getLat(JSONObject json) {
        try {
            JSONArray arr = (JSONArray) json.get("results");
            JSONObject resultsObject = (JSONObject) arr.get(0);
            JSONObject geometryObject = (JSONObject) resultsObject.get("geometry");
            JSONObject locationObject = (JSONObject) geometryObject.get("location");
            return (double) locationObject.get("lat");
        } catch (Exception e) {
            System.out.println("problem getting lat");
            String errorString = "";
            for (StackTraceElement element : e.getStackTrace()) {
                errorString += element + "\n";
            }
            System.out.println(errorString);

        }
        return (Double) 0.0;
    }

    public double getLon(JSONObject json) {
        try {
            JSONArray arr = (JSONArray) json.get("results");
            JSONObject resultsObject = (JSONObject) arr.get(0);
            JSONObject geometryObject = (JSONObject) resultsObject.get("geometry");
            JSONObject locationObject = (JSONObject) geometryObject.get("location");

            return (double) locationObject.get("lng");
        } catch (Exception e) {
            System.out.println("problem getting lng");
            String errorString = "";
            for (StackTraceElement element : e.getStackTrace()) {
                errorString += element + "\n";
            }
            System.out.println(errorString);
        }
        return (Double) 0.0;
    }

}

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GetWeather {

    private static final String api_key = "<API_KEY_HERE>"; //open weather api key
    private static final String location_endpoint = "http://api.openweathermap.org/data/2.5/weather?q=%s&APPID=%s"; //endpoint for location info
    private static final String zipcode_endpoint = "http://api.openweathermap.org/data/2.5/weather?zip=%s&APPID=%s";// endfpoint for zipcode info
    
    //uses a location name to fetch json data from REST and convert it into a string that prints the info we want cleanly
    public static String getInfoLocation(String location) {
        try {
            //combine proper endpoint with the api key
            URL url = new URL(String.format(location_endpoint,location,api_key));
            //open http connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //set request method to GET
            conn.setRequestMethod("GET");

            //create new reader to read in put from http request
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            //temp var to store stuff read in from reader
            String input;
            //var to hold actaul response
            StringBuffer content = new StringBuffer();

            //while request is sending info, copy the info into input and join them all together in append
            while ((input = in.readLine()) != null) {
                content.append(input);
            }
            //close reader
            in.close();
            //send json string to hadnleJSON to get formateed info string to print
            return handleJSON(content.toString());

        } catch (Exception e) {
            //catch exceptiona and print error message to console
            System.out.println("Failed to get weather data!");
        }
        //bot sends this string if it fails to get proper info for the location given for whatever reason
        return "Failed to get data for this location. Make sure location is correctly spelled. Abbreivations are not allowed";
    }
    
    //same process as above except use zipcode input isntead of location and use zipcode endpoint also
    public static String getInfoZipCode(String zipcode) {
        try {
            URL url = new URL(String.format(zipcode_endpoint,zipcode,api_key));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String input;
            StringBuffer content = new StringBuffer();

            while ((input = in.readLine()) != null) {
                content.append(input);
            }
            in.close();
            return handleJSON(content.toString());

        } catch (Exception e) {
            System.out.println("Failed to get weather data!");
        }
        return "Failed to get data for this zipcode. Only USA zipcodes are supported for now";
    }

    //takes in json string returned from GET request and formats it into string to print out
    public static String handleJSON(String json) {
        //create new json parser object
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        //find and store the main entry
        JsonObject main = obj.getAsJsonObject("main");
        //get the info needed like temp measruements
        double temp = main.get("temp").getAsDouble();
        double temp_max = main.get("temp_max").getAsDouble();
        double temp_min = main.get("temp_min").getAsDouble();
        String weather = obj.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
        String location = obj.get("name").getAsString();
        //send data to openweather class to convert everything from kelvin to fahrenheit and get neat string
        OpenWeatherData data = new OpenWeatherData(location, weather, temp, temp_min, temp_max);
        //return formated neat string
        return data.dataString();
    }
}

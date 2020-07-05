import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class StockTicker {

    private static final String endpoint = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=%s&apikey=%s";//endpoint for stick info api
    private static final String api_key = "BRRT4TP6RP68Z3SJ";//api key

    public static String getTickerInfo(String ticker) {
        try {
            //combine proper endpoint with the api key
            URL url = new URL(String.format(endpoint, ticker, api_key));
            //open http connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //set request method to get
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
            System.out.println("Failed to get ticker data!");
        }
        //bot sends this string if it fails to get proper info for the location given for whatever reason
        return "Failed to get data for this ticker. Make sure ticker is correctly spelled";
    }

    public static String handleJSON(String json) {
         //create new json parser object
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
        //find and store the global quote entry
        JsonObject quote = obj.getAsJsonObject("Global Quote");
        
        //get the info needed like stock open, high, etc
        String symbol = quote.get("01. symbol").getAsString();
        double open = quote.get("02. open").getAsDouble();
        double high = quote.get("03. high").getAsDouble();
        double low = quote.get("04. low").getAsDouble();
        double price = quote.get("05. price").getAsDouble();
        
        //return formated neat string with all the info
        return String.format("%s is currently trading at $%.3f. It opened at $%.3f, had a daily high of $%.3f and a daily low of $%.3f", symbol, price, open, high, low);

    }

}
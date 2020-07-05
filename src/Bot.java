import org.jibble.pircbot.*; //improt the pirbot library 

//inherit custom bot from PircBot properties
public class Bot extends PircBot {
    //bot constructor and sets bot name
    public Bot() {
        this.setName("ProjOne Bot Demo");
    }

    //function that returns true if the string is a number, false if not
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    //function is called automatically when any use types a message in the channel
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        //converts the msg to lower case in case user types using different caps locks
        message = message.toLowerCase();

        //is called if the message contains the keyword "weather"
        if (message.contains("weather")) {
            //converts the msg string into two parts(part1 : "weather", part2 : everything after "Weather")
            String[] split = message.split(" ", 2);
            
            //if the second part is a number then use the zipcode info
            if (isNumeric(split[1])) {
                String msgToSend = GetWeather.getInfoZipCode(split[1]);
                sendMessage(channel, msgToSend);
            }
            //otherwise use the normal location info
            else {
                String msgToSend = GetWeather.getInfoLocation(split[1]);
                sendMessage(channel, msgToSend);
            }
        }
        //is called when the msg string contains the keyword "ticker"
        if (message.contains("ticker")) {
            String[] split = message.split(" ", 2);
            String msgToSend = StockTicker.getTickerInfo(split[1]);
            sendMessage(channel, msgToSend);
        }
    }
}
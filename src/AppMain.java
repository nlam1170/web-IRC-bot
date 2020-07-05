public class AppMain {
    public static void main(String[] args) throws Exception {
        //create new bot instance
        Bot bot = new Bot();
       
        //bot settings and ctarget server to conenct to
        bot.setVerbose(true);
        bot.connect("irc.freenode.net");
        
        //channel name to connect to
        String channel_name = "#proj1demo";
        
        //bot joins channel and sends a quick msg that explains how to fetch the ticker and weather info
        bot.joinChannel(channel_name);
        bot.sendMessage(channel_name, "Use \"weather {zipcode/location}\" to get weather info! Use \"ticker {stock ticker}\" to get stock info!");
    }
}

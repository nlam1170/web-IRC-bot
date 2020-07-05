Hello and welcome to my CS2336 ProjectOne for KHAN. This project makes use of the IRC Web Client and REST request technoliges in order to demonstrate how to use them in order to create an interactive information getter.

For this project, I implemented two API's.

1. The OpenWeather API returns some weather information given either the name of a valid location of the numerics of a valid zipcode in the USA

2. The AlphaVantage API returns some trading inforamtion about any given valid stock Ticker such as "AAPL" or "IBM"

# How to USE
## OpenWeather API
Go to https://webchat.freenode.net/ in a browser. Enter a valid nickname and for channel name, make sure its the exact same as the channel_name var in `AppMain.java`
By default, this will be `#proj1demo`

Once you join the channel, you can now run `AppMain.java` and the bot should join the same channel.

You can now start making requests by typing "weather" followed by a valid location or zipcode.

For example, if wanted to get weather information for Houston, TX, USA, we would type 
`weather houston`
and the bot would return something like this
`The weather in Houston is Clouds. The temperature is 94.8°F, with a high of 97.3°F, and a low of 91.7°F.`

if we wanted to get weather information for New York City, New York, USA, we would type
`weather new york city`
and the bot would return something like this
`The weather in New York is Clear. The temperature is 93.3°F, with a high of 96.3°F, and a low of 89.9°F.`

If you wanted to get weather information using zipcode, you would type something like this. Not that only zipcodes in the USA are valid.
`weather 40003`
and the bot would return something like this
`The weather in Bagdad is Clouds. The temperature is 93.3°F, with a high of 93.5°F, and a low of 93.3°F.`

## AlphaVantage API
This API is also called through the freenode browser chat, but instead uses the keyword of "ticker" instead of "weather"

For example, if we wanted to get some stock information on the Apple Stock, we would type something like this
`ticker AAPL`
and the bot would return something like this
`AAPL is currently trading at $364.110. It opened at $367.850, had a daily high of $370.470 and a daily low of $363.640`

# Error Handling
The bot supports some basic error handling in case you enter an invalid weather location or weather zipcode or stock ticker.

This is assuming that "weather" or "ticker" are spelled correctly.
For example,
`weather ooga` is an error
but,
`wether houston` is not an error

Invalid location error msg:
`Failed to get data for this location. Make sure location is correctly spelled. Abbreivations are not allowed`

Invalid zipcode error msg:
`Failed to get data for this zipcode. Only USA zipcodes are supported for now`

Invalid stock ticer error msg:
`Failed to get data for this ticker. Make sure ticker is correctly spelled`
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

import java.util.Scanner;

public class WeatherApp {
    //hjdffs
    // Copy your API-KEY here
    public final static String apiKey = "d1ed4b8e2a554ae3958164620230103";

    // TODO: Write main function
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String city=sc.next();
        String str1=getWeatherData(city);
//        String str2=
        System.out.println(getTemperature( str1));
        System.out.println(getHumidity( str1));
    }

    /**
     * Retrieves weather data for the specified city.
     *
     * @param city the name of the city for which weather data should be retrieved
     * @return a string representation of the weather data, or null if an error occurred
     */
    public static String getWeatherData(String city) {
        try {
            URL url = new URL("http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO: Write getTemperature function returns celsius temperature of city by given json string
    public static double getTemperature(String weatherJson) {
        double answer = 0.0;
        JSONObject weatherObject = new JSONObject(weatherJson);
        answer = weatherObject.getJSONObject("current").getDouble("temp_c");

        return answer;
    }

    // TODO: Write getHumidity function returns humidity percentage of city by given json string
    public static int getHumidity(String weatherJson) {
        int answer = 0;
        JSONObject getHumidity=new JSONObject(weatherJson);
        answer=getHumidity.getJSONObject("current").getInt("humidity");


        return answer;
    }
}

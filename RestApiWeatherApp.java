import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RestApiWeather {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        // Prompt user for city name
        System.out.print("Enter the name of the city: ");
        String city = userInput.nextLine();

        // API key and endpoint
        String apiKey = "9dd1c1beb5331932dbfac3ce8c2ffbb3"; // Replace with your OpenWeatherMap API key
        String apiUrl = "http://api.openweathermap.org/data/2.8.9/weather?q=" + city + "&appid=" + apiKey;

        try {
            // Establish HTTP connection
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Check if response is OK
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Error: Unable to fetch weather data. HTTP Response Code: " + responseCode);
                return;
            }

            // Read response from API
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder inline = new StringBuilder();
            while (scanner.hasNext()) {
                inline.append(scanner.nextLine());
            }
            scanner.close();

            // Parse JSON response
            JsonObject jsonObject = JsonParser.parseString(inline.toString()).getAsJsonObject();
            JsonObject main = jsonObject.getAsJsonObject("main");
            JsonObject weather = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject();

            // Display structured data
            System.out.println("\nWeather Details:");
            System.out.println("City: " + city);
            System.out.println("Temperature: " + (main.get("temp").getAsDouble() - 273.15) + " °C");
            System.out.println("Weather: " + weather.get("description").getAsString());
            System.out.println("Humidity: " + main.get("humidity").getAsInt() + " %");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


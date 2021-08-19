import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import static io.restassured.RestAssured.*;

public class WeatherService {

    private static List<Float> getDailyMorningTemperatureList(int numberOfDays, double lat, double lon, String apiKey) {
        List<Float> temperatureList = new ArrayList(numberOfDays);

        Response response = given()
                .when()
                .get("https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon +
                        "&exclude=current,minutely,hourly,alerts&units=metric&appid=" + apiKey)
                .then()
                .statusCode(200)
                .extract()
                .response();

        for (int i = 0; i < numberOfDays; i++) {
            temperatureList.add((Float) ((LinkedHashMap) ((LinkedHashMap) response.jsonPath().getList("daily").get(i)).get("temp")).get("morn"));
        }

        return temperatureList;
    }

    public static double getAverageMorningTemperatureForecast(int numberOfDays, double lat, double lon, String apiKey) {

        List<Float> temperatureList = getDailyMorningTemperatureList(numberOfDays, lat, lon, apiKey);

        double avg = 0;
        for (double temp : temperatureList) {
            avg += temp;
        }
        return avg /= temperatureList.size();
    }

    public static double getMaximumMorningTemperatureForecast(int numberOfDays, double lat, double lon, String apiKey) {

        List<Float> temperatureList = getDailyMorningTemperatureList(numberOfDays, lat, lon, apiKey);

        double max = -273;
        for (double temp : temperatureList) {
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }
}


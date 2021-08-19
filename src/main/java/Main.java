public class Main {

    public static void main(String[] args) {

        int numberOfDays = 5;
        double myCityLat = 59.932054;
        double myCityLon = 30.300899;
        String myApiKey = "6578d24d958aaa23059fce9612b6f551";

        System.out.println("Среднее значение утренних температур за предстоящие, включая сегодня, " +
                numberOfDays + " дней: " +
                String.format("%.2f", WeatherService.getAverageMorningTemperatureForecast(numberOfDays, myCityLat, myCityLon, myApiKey)));
        System.out.println("Максимальная температура утром за предстоящие, включая сегодня, " +
                numberOfDays + " дней: " +
                String.format("%.2f", WeatherService.getMaximumMorningTemperatureForecast(numberOfDays, myCityLat, myCityLon, myApiKey)));
    }
}

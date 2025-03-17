import java.util.List;

/**
 * Record for app entry point
 * Contains main method using modern java features like text blocks & lambdas
 */
public record Main() {
    public static void main(String[] args) {
        List<WeatherData> data = WeatherAnalyzer.loadData("src/weather.csv");
        double avgTemp = WeatherAnalyzer.avgTemp(data , 7);
        long rainyDays = WeatherAnalyzer.countRainyDays(data);
        List<WeatherData> hotDays = WeatherAnalyzer.tempThresh(data , 85.0);
        System.out.println("""
            ## Weather Data Analysis

            ☁Average Temp for July☁: %f

            ☂Rainy Days Count☂: %d

            ☀Days Above 85°☀: %s
            """.formatted(avgTemp , rainyDays , hotDays));
        System.out.println("Weather Categories:");
        data.forEach(d -> System.out.println(d.date() + " - " + WeatherAnalyzer.categorizeTemperature(d.temperature())));
    }
}
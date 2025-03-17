import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Interface for weather analysis
 * Uses modern java features like lambdas text blocks & enhanced switch
 *
 * Example:
 * List<WeatherData> data =WeatherAnalyzer.loadData("weather.csv");
 * double avg = WeatherAnalyzer.avgTemp(data , 7);
 */
public interface WeatherAnalyzer{

    /**
     * Load weather data from a CSV file
     *
     * CSV format:
     * date,temperature,humidity,precipitation
     *
     * Example:
     * List<WeatherData> data = WeatherAnalyzer.loadData("weather.csv");
     *
     * @param path path to CSV file
     * @return list of WeatherData records
     */
    static List<WeatherData> loadData(String path){
        try{
            return Files.lines(Path.of(path))
                    .skip(1)
                    .map(line ->{
                        String[] parts =line.split(",");
                        for (int i =0;i<parts.length;i++){
                            parts[i] =parts[i].trim().replaceAll("^\"|\"$", "");
                        }
                        String[] dateParts =parts[0].split("-");
                        int month =Integer.parseInt(dateParts[1]);
                        return new WeatherData(parts[0], month,
                                Double.parseDouble(parts[1]),
                                Double.parseDouble(parts[2]),
                                Double.parseDouble(parts[3]));
                    })
                    .collect(Collectors.toList());
        } catch(Exception e){
            throw new RuntimeException("Failed to load data : "+ e.getMessage());
        }
    }
    /**
     * Returns avg temp for the given month
     *
     * Example:
     * double avg =WeatherAnalyzer.avgTemp(data , 7);
     *
     *
     * @param data list of weather data
     * @param month month number (1-12)
     * @return average temperature
     */
    static double avgTemp(List<WeatherData> data ,int month){
        return data.stream()
                .filter(d ->d.month() ==month)
                .mapToDouble(WeatherData::temperature)
                .average()
                .orElse(Double.NaN);
    }

    /**
     * Returns list of days w/ temperature above threshold
     *
     * Example:
     * List<WeatherData> hotDays = WeatherAnalyzer.tempThresh(data , 30.0);
     *
     * @param data list of WeatherData
     * @param threshold temperature threshold
     * @return list of WeatherData records
     */
    static List<WeatherData> tempThresh(List<WeatherData> data ,double threshold){
        return data.stream()
                .filter(d ->d.temperature()>threshold)
                .collect(Collectors.toList());
    }
    /**
     * Returns count of rainy days
     *
     * Example:
     * long rainy = WeatherAnalyzer.countRainyDays(data);
     *
     * @param data list of WeatherData
     * @return number of days w/ precipitation > 0.0
     */
    static long countRainyDays(List<WeatherData> data){
        return data.stream()
                .filter(d -> d.precipitation()>0.0)
                .count();
    }

    /**
     * Categorize temp into a weather category using enhanced switch
     *
     * Example:
     * String cat = WeatherAnalyzer.categorizeTemperature(28.5);
     *
     * @param temperature temperature value
     * @return "Hot" if >= 30  "Warm" if between 20 & 30  "Cold" otherwise
     */
    static String categorizeTemperature(double temperature){
        return switch ((int) temperature){
            case int t when t>= 85-> "Hot";
            case int t when t>=60 -> "Warm";
            default ->"Cold";
        };
    }
}

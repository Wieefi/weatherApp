/**
 * Record representin weather data
 * Contains date month temperature humidity & precipitation
 *
 * Example:
 * WeatherData wd = new WeatherData("2023-07-15" , 7 , 30.5 , 60.0 , 0.0);
 */
public record WeatherData(String date,int month , double temperature,double humidity ,double precipitation){

}

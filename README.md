# Weather Data Analyzer

A modern Weather Data Analyzer app built using Java 15-23 features like records, interfaces, enhanced switch, lambdas & text blocks. It parses weather data from a CSV file & analyzes it with minimal, functional-style code.

## Features
- **CSV Parsing** – Reads weather data (date, temperature, humidity, precipitation)
- **Average Temperature** – Computes avg temp for a specific month
- **Hot Days & Rainy Days** – Filters days by a temperature threshold & counts rainy days
- **Weather Categorization** – Uses enhanced switch to label days as Hot/Warm/Cold
- **Modern Design** – Uses records, interfaces & functional programming (no explicit classes!)

## Setup
1. Clone the repo:
   ```bash
   git clone https://github.com/yourusername/weather-data-analyzer.git
   cd weather-data-analyzer

## Code Overview
WeatherData Record – Represents a weather entry (date, month, temperature, humidity, precipitation)
WeatherAnalyzer Interface – Offers static methods to load CSV data, calculate averages, filter & count days, & categorize weather using enhanced switch
App Record – Entry point that loads data & prints analysis using text blocks
Example Code Usage
java
Copy
List<WeatherData> data = WeatherAnalyzer.loadData("weather.csv");
double avgTemp = WeatherAnalyzer.averageTemperature(data, 7);
long rainyDays = WeatherAnalyzer.countRainyDays(data);
System.out.println("""
    ## Weather Analysis
    **Avg Temp for July:** %f°F
    **Rainy Days:** %d
    """.formatted(avgTemp, rainyDays));
## Generating Documentation
Generate Javadoc with markdown support (requires JDK 18+):

bash
Copy
javadoc -d docs -sourcepath src/main/java -subpackages com.example.weather

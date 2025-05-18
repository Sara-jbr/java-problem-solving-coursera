package org.coursera.duke.solvingproblems.module4;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WeatherAnalyzerTester {
    public static void main(String[] args) throws IOException {
        WeatherAnalyzer analyzer = new WeatherAnalyzer();
        String folderPath = "src/main/resources/nc_weather/2013";

        // 1. Test coldestHourInFile
        Reader reader1 = Files.newBufferedReader(Paths.get(folderPath, "weather-2013-07-22.csv"));
        CSVParser parser1 = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader1);
        CSVRecord coldest = analyzer.coldestHourInFile(parser1);
        System.out.println("Coldest temp on weather-2013-07-22.csv was " + coldest.get("TemperatureF") + " at " + coldest.get("DateUTC"));
        reader1.close();

        // 2. Test fileWithColdestTemperature
        String coldestFileName = analyzer.fileWithColdestTemperature(folderPath);
        System.out.println("Coldest day was in file: " + coldestFileName);

        Path coldestFullPath = Paths.get(folderPath, coldestFileName);
        Reader reader = Files.newBufferedReader(coldestFullPath);
        CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        CSVRecord coldest1 = analyzer.coldestHourInFile(parser);
        System.out.println("Coldest temperature in 2013 was: " + coldest1.get("TemperatureF") + " at " + coldest1.get("DateUTC"));
        reader.close();

        // 3. Test lowestHumidityInFile
        Reader reader2 = Files.newBufferedReader(Paths.get(folderPath, "weather-2013-07-22.csv"));
        CSVParser parser2 = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader2);
        CSVRecord lowestHumidity = analyzer.lowestHumidityInFile(parser2);
        System.out.println("Lowest humidity on weather-2013-07-22.csv was " + lowestHumidity.get("Humidity") + " at " + lowestHumidity.get("DateUTC"));
        reader2.close();

        // 4. Test lowestHumidityInManyFiles
        CSVRecord lowestHumidityMany = analyzer.lowestHumidityInManyFiles(folderPath);
        System.out.println("Lowest humidity in 2013 was " + lowestHumidityMany.get("Humidity") + " at " + lowestHumidityMany.get("DateUTC"));

        // 5. Test averageTemperatureInFile
        Reader reader3 = Files.newBufferedReader(Paths.get(folderPath, "weather-2013-07-22.csv"));
        CSVParser parser3 = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader3);
        double avgTemp = analyzer.averageTemperatureInFile(parser3);
        System.out.println("Average temp on weather-2014-07-22.csv was: " + avgTemp);
        reader3.close();

        // 6. Test averageTemperatureWithHighHumidityInFile
        Reader reader4 = Files.newBufferedReader(Paths.get(folderPath, "weather-2013-07-22.csv"));
        CSVParser parser4 = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader4);
        double avgTempHighHumidity = analyzer.averageTemperatureWithHighHumidityInFile(parser4, 80);
        if (Double.isNaN(avgTempHighHumidity)) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average temperature when humidity >= 80 on weather-2013-07-22.csv was: " + avgTempHighHumidity);
        }
        reader4.close();

        // --- Question 7: What time of day did the lowest humidity in 2014 occur? ---
        System.out.println("\nQuestion 7:");
        System.out.println("What time of day did the lowest humidity in 2013 occur?");
        String dateTime = lowestHumidityMany.get("DateUTC");
        String timeOfDay = dateTime.split(" ")[1]; // Extract time part only
        System.out.println("Answer: " + timeOfDay); // e.g., "13:51:00"
    }
}

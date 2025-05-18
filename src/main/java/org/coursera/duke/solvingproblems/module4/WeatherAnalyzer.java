package org.coursera.duke.solvingproblems.module4;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WeatherAnalyzer {



    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldest = null;
        for (CSVRecord record : parser) {
            String tempStr = record.get("TemperatureF");
            if (tempStr.equals("-9999")) continue;
            double temp = Double.parseDouble(tempStr);
            if (coldest == null || temp < Double.parseDouble(coldest.get("TemperatureF"))) {
                coldest = record;
            }
        }
        return coldest;
    }

    // Find the file with the coldest temperature
    public String fileWithColdestTemperature(String directoryPath) throws IOException {
        CSVRecord coldestRecord = null;
        String coldestFile = null;

        DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath), "*.csv");
        for (Path path : stream) {
            Reader reader = Files.newBufferedReader(path);
            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            CSVRecord currentRecord = coldestHourInFile(parser);
            if (currentRecord == null) continue;
            double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
            if (coldestRecord == null || currentTemp < Double.parseDouble(coldestRecord.get("TemperatureF"))) {
                coldestRecord = currentRecord;
                coldestFile = path.getFileName().toString();
            }
            reader.close();
        }
        return coldestFile;
    }

    // Find the lowest humidity in a file
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowest = null;
        for (CSVRecord record : parser) {
            String humidityStr = record.get("Humidity");
            if (humidityStr.equals("N/A")) continue;
            double humidity = Double.parseDouble(humidityStr);
            if (lowest == null || humidity < Double.parseDouble(lowest.get("Humidity"))) {
                lowest = record;
            }
        }
        return lowest;
    }

    // Find the lowest humidity across multiple files
    public CSVRecord lowestHumidityInManyFiles(String directoryPath) throws IOException {
        CSVRecord lowest = null;

        DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath), "*.csv");
        for (Path path : stream) {
            Reader reader = Files.newBufferedReader(path);
            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            CSVRecord currentRecord = lowestHumidityInFile(parser);
            if (currentRecord == null) continue;
            double currentHumidity = Double.parseDouble(currentRecord.get("Humidity"));
            if (lowest == null || currentHumidity < Double.parseDouble(lowest.get("Humidity"))) {
                lowest = currentRecord;
            }
            reader.close();
        }
        return lowest;
    }

    // Average temperature in a file
    public double averageTemperatureInFile(CSVParser parser) {
        double sum = 0.0;
        int count = 0;
        for (CSVRecord record : parser) {
            String tempStr = record.get("TemperatureF");
            if (tempStr.equals("-9999")) continue;
            double temp = Double.parseDouble(tempStr);
            sum += temp;
            count++;
        }
        return count == 0 ? 0.0 : sum / count;
    }

    // Average temperature with humidity >= threshold
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int humidityThreshold) {
        double sum = 0.0;
        int count = 0;
        for (CSVRecord record : parser) {
            String humidityStr = record.get("Humidity");
            String tempStr = record.get("TemperatureF");
            if (humidityStr.equals("N/A") || tempStr.equals("-9999")) continue;
            double humidity = Double.parseDouble(humidityStr);
            if (humidity >= humidityThreshold) {
                double temp = Double.parseDouble(tempStr);
                sum += temp;
                count++;
            }
        }
        return count == 0 ? Double.NaN : sum / count;
    }
}



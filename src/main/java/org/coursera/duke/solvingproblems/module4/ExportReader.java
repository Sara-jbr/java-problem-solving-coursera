package org.coursera.duke.solvingproblems.module4;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ExportReader {

    public void tester() {
        FileResource fr = new FileResource();

        CSVParser parser = fr.getCSVParser();
        System.out.println("Country Info:");
        System.out.println(countryInfo(parser, "Germany"));

        parser = fr.getCSVParser();
        System.out.println("\nCountries exporting both gold and diamonds:");
        listExportersTwoProducts(parser, "cotton", "flowers");

        parser = fr.getCSVParser();
        System.out.println("\nNumber of countries exporting cocoa:");
        System.out.println(numberOfExporters(parser, "cocoa"));

        parser = fr.getCSVParser();
        System.out.println("\nCountries with big export values:");
        bigExporters(parser, "$999,999,999");
    }

    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            String countryName = record.get("Country");
            if (countryName.equalsIgnoreCase(country)) {
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                return countryName + ": " + exports + ": " + value;
            }
        }
        return "NOT FOUND";
    }
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem)) {
                count++;
            }
        }
        return count;
    }
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()) {
                System.out.println(record.get("Country") + " " + value);
            }
        }
    }

    public static void main(String[] args) {
        ExportReader reader = new ExportReader();
        reader.tester();
    }
}





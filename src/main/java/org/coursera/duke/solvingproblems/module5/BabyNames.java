package org.coursera.duke.solvingproblems.module5;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.*;

import java.io.*;

public class BabyNames {

    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int uniqueGirls = 0;
        int uniqueBoys = 0;

        for (CSVRecord record : fr.getCSVParser(false)) {
            String gender = record.get(1);
            int births = Integer.parseInt(record.get(2));

            totalBirths += births;
            if (gender.equals("F")) {
                totalGirls += births;
                uniqueGirls++;
            } else {
                totalBoys += births;
                uniqueBoys++;
            }
        }

        System.out.println("Total births = " + totalBirths);
        System.out.println("Total girls = " + totalGirls + " (Unique: " + uniqueGirls + ")");
        System.out.println("Total boys = " + totalBoys + " (Unique: " + uniqueBoys + ")");
        System.out.println("Total names = " + (uniqueGirls + uniqueBoys));
    }

    public int getRank(int year, String name, String gender) {
        String filename = "src/main/resources/data/yob" + year + ".csv";
        FileResource fr = new FileResource(filename);
        int rank = 0;

        for (CSVRecord record : fr.getCSVParser(false)) {
            String currentGender = record.get(1);
            if (currentGender.equals(gender)) {
                rank++;
                if (record.get(0).equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }

    public String getName(int year, int rank, String gender) {
        String filename = "src/main/resources/data/yob" + year + ".csv";
        FileResource fr = new FileResource(filename);
        int currentRank = 0;

        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                currentRank++;
                if (currentRank == rank) {
                    return record.get(0);
                }
            }
        }

        return "NO NAME";
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if they were born in " + newYear + ".");
    }

    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestRank = Integer.MAX_VALUE;
        int yearOfHighest = -1;

        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.replaceAll("[^0-9]", ""));
            FileResource fr = new FileResource(f);
            int rank = getRankFromFile(fr, name, gender);

            if (rank != -1 && rank < highestRank) {
                highestRank = rank;
                yearOfHighest = year;
            }
        }

        return yearOfHighest == -1 ? -1 : yearOfHighest;
    }

    private int getRankFromFile(FileResource fr, String name, String gender) {
        int rank = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
            if (record.get(1).equals(gender)) {
                rank++;
                if (record.get(0).equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }

    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int totalRank = 0;
        int count = 0;

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int rank = getRankFromFile(fr, name, gender);
            if (rank != -1) {
                totalRank += rank;
                count++;
            }
        }

        return count == 0 ? -1.0 : (double) totalRank / count;
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        FileResource fr = new FileResource("src/main/resources/data/yob" + year + ".csv");
        int total = 0;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                if (rec.get(0).equals(name)) {
                    break;
                }
                total += Integer.parseInt(rec.get(2));
            }
        }
        return total;
    }


}


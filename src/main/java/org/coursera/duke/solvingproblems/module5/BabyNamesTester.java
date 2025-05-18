package org.coursera.duke.solvingproblems.module5;

import edu.duke.*;



public class BabyNamesTester {
    public static void main(String[] args) {
        BabyNames bn = new BabyNames();

        System.out.println("== totalBirths ==");
        bn.totalBirths(new FileResource("src/main/resources/data/yob1990.csv"));

        System.out.println("\n== getRank ==");
        System.out.println("Rank of Emily (F, 1960): " + bn.getRank(1960, "Emily", "F"));
        System.out.println("Rank of Frank (M, 1971): " + bn.getRank(1971, "Frank", "M"));

        System.out.println("\n== getName ==");
        System.out.println("Girl's name of rank 350 in 1980: " + bn.getName(1980, 350, "F"));
        System.out.println("Boy's name of rank 450 in 1982: " + bn.getName(1982, 450, "M"));

        System.out.println("\n== whatIsNameInYear ==");
        bn.whatIsNameInYear("Susan", 1972, 2014, "F");
        bn.whatIsNameInYear("Owen", 1974, 2014, "M");

        System.out.println("\n== yearOfHighestRank ==");
        System.out.println("Highest rank year for Genevieve (F): " + bn.yearOfHighestRank("Genevieve", "F"));
        System.out.println("Highest rank year for Mich (M): " + bn.yearOfHighestRank("Mich", "M"));

        System.out.println("\n== getAverageRank ==");
        System.out.println("Average rank of Jacob (M): " + bn.getAverageRank("Susan", "F"));
        System.out.println("Average rank of Jacob (M): " + bn.getAverageRank("Robert", "M"));

        System.out.println("\n== getTotalBirthsRankedHigher ==");
        System.out.println("Total births ranked higher than Drew (M, 1990): " +
                bn.getTotalBirthsRankedHigher(1990, "Drew", "M"));


    }
}

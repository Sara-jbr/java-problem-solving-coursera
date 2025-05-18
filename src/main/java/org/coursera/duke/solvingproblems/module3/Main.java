package org.coursera.duke.solvingproblems.module3;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Part 1 ===");
        Part1 part1 = new Part1();
        part1.testFindStopCodon();
        part1.testFindGene();
        part1.printAllGenes("ATGTAAGATGCCCTAGT");

        System.out.println("\n=== Part 2 ===");
        Part2 part2 = new Part2();
        part2.testHowMany();

        System.out.println("\n=== Part 3 ===");
        Part3 part3 = new Part3();
        part3.testCountGenes();
    }
}

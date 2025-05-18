package org.coursera.duke.solvingproblems.module3;

public class Part1 {

    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }

    public void testFindStopCodon() {
        String dna = "ATGxxxyyyTAAxxxyyyTGA";
        System.out.println(findStopCodon(dna, 0, "TAA")); // should print 9
        System.out.println(findStopCodon(dna, 0, "TGA")); // should print 21
        System.out.println(findStopCodon(dna, 0, "TAG")); // should print dna.length() => 22
    }

    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }

        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if (minIndex == dna.length()) {
            return "";
        }

        return dna.substring(startIndex, minIndex + 3);
    }

    public void testFindGene() {
        String[] testCases = {
                "AAATGCCCTAACTAGATTGAAACC", // ATGCCC...TAA → should return a gene
                "AAACCCTAACTAGATTGAAACC",   // No ATG → should return ""
                "ATGCCCTAGATGTAA",          // Two stop codons, return the closest one
                "ATGCCCCCCCC",              // No valid stop codon → ""
                "ATGCCCGGGTTTAAATAGTGA"     // Multiple genes possible
        };

        for (String dna : testCases) {
            System.out.println("DNA: " + dna);
            System.out.println("Gene: " + findGene(dna));
            System.out.println();
        }
    }

    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            int atgIndex = dna.indexOf("ATG", startIndex);
            if (atgIndex == -1) break;

            int taaIndex = findStopCodon(dna, atgIndex, "TAA");
            int tagIndex = findStopCodon(dna, atgIndex, "TAG");
            int tgaIndex = findStopCodon(dna, atgIndex, "TGA");

            int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
            if (minIndex == dna.length()) {
                startIndex = atgIndex + 3;
                continue;
            }

            System.out.println("Gene found: " + dna.substring(atgIndex, minIndex + 3));
            startIndex = minIndex + 3;
        }
    }
}

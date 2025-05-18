package org.coursera.duke.solvingproblems.module3;

public class Part3 {

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

    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) return "";

        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if (minIndex == dna.length()) return "";

        return dna.substring(startIndex, minIndex + 3);
    }

    public int countGenes(String dna) {
        int count = 0;
        int startIndex = 0;
        while (true) {
            String gene = findGene(dna, startIndex);
            if (gene.isEmpty()) break;

            count++;
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
        return count;
    }

    public void testCountGenes() {
        String[] samples = {
                "ATGTAAGATGCCCTAGT",        // 2
                "ATGAAATGAAAA",             // 2
                "ATGATCTAATTTATGCTGCAACGGTGAAGA", // 2
                "ATGATCATAAGAAGATAATAGAGGGCCATGTAA" // 2
        };

        for (String dna : samples) {
            System.out.println("DNA: " + dna);
            System.out.println("Number of genes: " + countGenes(dna));
            System.out.println();
        }
    }
}


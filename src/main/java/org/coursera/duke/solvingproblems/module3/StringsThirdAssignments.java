package org.coursera.duke.solvingproblems.module3;

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class StringsThirdAssignments {

    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0) {
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

    public StorageResource getAllGenes(String dna) {
        dna = dna.toUpperCase();  // Add this to normalize input
        StorageResource geneList = new StorageResource();
        int startIndex = 0;

        while (true) {
            String gene = findGene(dna, startIndex);
            if (gene.isEmpty()) break;
            geneList.add(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }

        return geneList;
    }

    public void processGenes(StorageResource sr) {
        int longGenesCount = 0;
        int highCgCount = 0;
        int longest = 0;

        System.out.println("All genes found:");
        for (String gene : sr.data()) {
            System.out.println(gene + " (length: " + gene.length() + ", CG ratio: " + cgRatio(gene) + ")");
        }

        System.out.println("\nGenes longer than 60 characters:");
        for (String gene : sr.data()) {
            if (gene.length() > 60) {
                System.out.println(gene);
                longGenesCount++;
            }
        }
        System.out.println("Number of genes longer than 60 characters: " + longGenesCount);

        System.out.println("\nGenes with C-G ratio > 0.35:");
        for (String gene : sr.data()) {
            if (cgRatio(gene) > 0.35) {
                System.out.println(gene);
                highCgCount++;
            }
        }
        System.out.println("Number of genes with C-G ratio > 0.35: " + highCgCount);

        for (String gene : sr.data()) {
            if (gene.length() > longest) {
                longest = gene.length();
            }
        }
        System.out.println("Length of the longest gene: " + longest);
    }


    public double cgRatio(String dna) {
        int count = 0;
        for (char c : dna.toCharArray()) {
            if (c == 'C' || c == 'G') count++;
        }
        return (double) count / dna.length();
    }

    public int countCTG(String dna) {
        int count = 0;
        int index = dna.indexOf("CTG");
        while (index != -1) {
            count++;
            index = dna.indexOf("CTG", index + 3);
        }
        return count;
    }

//    public void processGenes(StorageResource sr) {
//        int longGenesCount = 0;
//        int highCgCount = 0;
//        int longest = 0;
//
//        System.out.println("Genes longer than 60 characters:");
//        for (String gene : sr.data()) {
//            if (gene.length() > 60) {
//                System.out.println(gene);
//                longGenesCount++;
//            }
//        }
//        System.out.println("Number of genes longer than 60 characters: " + longGenesCount);
//
//        System.out.println("\nGenes with C-G ratio > 0.35:");
//        for (String gene : sr.data()) {
//            if (cgRatio(gene) > 0.35) {
//                System.out.println(gene);
//                highCgCount++;
//            }
//        }
//        System.out.println("Number of genes with C-G ratio > 0.35: " + highCgCount);
//
//        for (String gene : sr.data()) {
//            if (gene.length() > longest) {
//                longest = gene.length();
//            }
//        }
//        System.out.println("Length of the longest gene: " + longest);
//    }

    // New method to count how many genes in the DNA string
    public int countGenes(String dna) {
        StorageResource genes = getAllGenes(dna);
        int count = 0;
        for (String gene : genes.data()) {
            count++;
        }
        return count;
    }

    public void testCountGenes() {
        String dna = "ATGAAATGAAAA";  // example DNA string
        int geneCount = countGenes(dna);
        System.out.println("Number of genes: " + geneCount);
    }

    public void testProcessGenes() {
        String dna1 = "ATGCCCGGGAAATAACCCATGCGGTGA"; // long genes
        String dna2 = "ATGTAAATGTAG"; // no long genes
        String dna3 = "ATGCGCGCGCGCGTAA"; // high CG ratio
        String dna4 = "ATGAAATAAATGTTTTAA"; // low CG ratio
        String dna5 = "ATGTTTGGGTAATAGTGAATGCCCCTGA"; // mix

        StorageResource sr1 = getAllGenes(dna1);
        StorageResource sr2 = getAllGenes(dna2);
        StorageResource sr3 = getAllGenes(dna3);
        StorageResource sr4 = getAllGenes(dna4);
        StorageResource sr5 = getAllGenes(dna5);

        System.out.println("Test Case 1:");
        processGenes(sr1);

        System.out.println("\nTest Case 2:");
        processGenes(sr2);

        System.out.println("\nTest Case 3:");
        processGenes(sr3);

        System.out.println("\nTest Case 4:");
        processGenes(sr4);

        System.out.println("\nTest Case 5:");
        processGenes(sr5);

        // Real DNA file
        FileResource fr = new FileResource("src/main/resources/brca1line.fa.txt");
        String realDNA = fr.asString();
        StorageResource genes = getAllGenes(realDNA);
        System.out.println("\nReal DNA Test:");
        processGenes(genes);
    }

    public void testCountCTG() {
        String dna = "CTGCTGATGCTGTTTCTG";
        System.out.println("CTG count: " + countCTG(dna));
    }

    public void testCgRatio() {
        String dna = "ATGCCATAG";
        System.out.println("CG Ratio: " + cgRatio(dna));
    }

    public static void main(String[] args) {

        StringsThirdAssignments obj = new StringsThirdAssignments();
        obj.testProcessGenes();
        obj.testCountCTG();
        obj.testCgRatio();
        obj.testCountGenes();  // Added test for counting genes
     }
}

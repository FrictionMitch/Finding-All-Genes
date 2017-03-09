import edu.duke.*;

import java.util.Scanner;

public class AllCodons {

    int taaIndex;
    int tagIndex;
    int tgaIndex;

    public int findStopCodon(String dnaString, int startIndex, String stopCodon) {
        int currentIndex = dnaString.indexOf(stopCodon, startIndex + stopCodon.length());
        while (currentIndex != -1) {
            int difference = currentIndex - startIndex;
            if (difference % 3 == 0) {
                return currentIndex;
            } else {
//                currentIndex += 1;
                currentIndex = dnaString.indexOf(stopCodon, currentIndex + 1);
            }
        }

//        return dnaString.length();
        return -1;
    }

    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        taaIndex = findStopCodon(dna, startIndex, "TAA");
        tagIndex = findStopCodon(dna, startIndex, "TAG");
        tgaIndex = findStopCodon(dna, startIndex, "TGA");

//        int minIndex = Math.min(taaIndex, (Math.min(tgaIndex, tagIndex)));
        int minIndex = 0;
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < tagIndex)) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }

        if (minIndex == -1 || tagIndex != -1 && tagIndex < minIndex) {
            minIndex = tagIndex;
        }
//        int minIndex =  Math.min(tempMinIndex, tgaIndex);

//        if (minIndex == dna.length()) {
//            return "";
//        }
        if (minIndex == -1) {
            return "";
        }

        return dna.substring(startIndex, minIndex + 3);
    }

    public void testFindStop() {

        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        String dna2 = "xxxyyyzzzTAATAGTGAzzzTAAxx";
        int dex = findStopCodon(dna, 0, "TAA");
        if (dex !=9) {
            System.out.println("Error on 9");
        } else {
            System.out.println("Test 1 successful");
        }

        dex = findStopCodon(dna, 9, "TAA");
        if (dex != 21) {
            System.out.println("Error on 21");
        } else {
            System.out.println("Test 2 successful");
        }

        dex = findStopCodon(dna, 1, "TAA");
        if (dex != -1) {
            System.out.println("Error on 26");
        } else {
            System.out.println("Test 3 successful");
        }

        dex = findStopCodon(dna, 0, "TAG");
        if (dex != -1) {
            System.out.println("Error on 26");
        } else {
            System.out.println("Test 4 successful");
        }

        int allIndex = findStopCodon(dna2, 0, "TAA");
        System.out.println("The first stop codon was at position: " + allIndex);
        System.out.println("TAA index at " + taaIndex);

        System.out.println("Find \"All Tests\" complete");
    }

    public void printAllGenes(String dna){
        // start index at 0;
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }

    }

    public void testOn(String dna) {
        System.out.println("Testing \"printAllGenes\" on " + dna);
        printAllGenes(dna);
    }

    public void test() {
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }

    public void testGene() {
        String dna = "ATGCCCGGGAAATAACCC";
        String gene = findGene(dna,0);
        if (!gene.equals("ATGCCCGGGAAATAA")) {
            System.out.println("You fucked up");
        } else {
            System.out.println("\"Gene Tests\" passed");
        }
        System.out.println("Tests completed");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name:");
//        String name = scanner.nextLine();
//        System.out.println("Hello, " + name);
    }
}

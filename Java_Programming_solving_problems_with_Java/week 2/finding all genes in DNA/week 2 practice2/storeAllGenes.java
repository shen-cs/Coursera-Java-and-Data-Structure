
/**
 * Write a description of storeAllGenes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class storeAllGenes {
     public int findStopIndex (String dna, int index) {
        String DNA = dna.toUpperCase();
        int stop1 = DNA.indexOf("TAG", index);
        if(stop1 == -1 || (stop1 - index) % 3 != 0) {
           stop1 = DNA.length();
        }
        int stop2 = DNA.indexOf("TGA", index);
        if(stop2 == -1 || (stop2 - index) % 3 != 0) {
           stop2 = DNA.length();
        } 
       int stop3 = DNA.indexOf("TAA", index);
        if(stop3 == -1 || (stop3 - index) % 3 != 0) {
           stop3 = DNA.length();
        }
        if(stop1 == stop2 && stop2 == stop3 && stop3 == DNA.length()) {
           return -1;
        }
        return Math.min(stop1, Math.min(stop2, stop3));
     }
      public StorageResource storeAll(String dna) {
       StorageResource genes = new StorageResource();
       String DNA = dna.toUpperCase();
       int start = 0;
       while(true) {
           int loc = DNA.indexOf("ATG", start);
           int stopIndex = findStopIndex(DNA, loc + 3);
           if(loc == -1) {
               break;
            }
           else if(stopIndex != -1) {
              genes.add(DNA.substring(loc, stopIndex + 3));
              start = stopIndex + 3;
            }
           else if(stopIndex == -1) {
              start = loc + 3;
            }
        }
        return genes;
     } 
     public double cgRatio (String dna) {
          String DNA = dna.toUpperCase();
          int count = 0;
          int loc = 0;
          int start = 0;
          while(loc != -1) {
            loc = DNA.indexOf("C", start);
            if(loc != -1) {
              count++;
            }
            start = loc + 1;
            }
          loc = start = 0;
          while(loc != -1) {
            loc = DNA.indexOf("G", start);
            if(loc != -1) {
              count++;
            }
            start = loc + 1;
            }
          return (count + 0.0) / DNA.length();
        }
     public void printGenes(StorageResource sr) {
         int numberOfGenes60 = 0;
         int numberOfGenesCG = 0;
          for(String gene: sr.data()) {
               if(gene.length() > 60) {
                numberOfGenes60 ++;
                System.out.println(gene.length() + "\t"+ gene);
                }
            }
          System.out.println("The number of genes with more than 60 nucleotides is " + numberOfGenes60);
          for(String gene: sr.data()) {
               if(cgRatio(gene) > 0.35) {
                numberOfGenesCG ++;
                System.out.println(gene.length() + "\t"+ gene);
                }
            }
          System.out.println("The number of genes whose C-G ratio are bigger than 0.35 is " + numberOfGenesCG);
          
        }
     public void testStorageFinder() {
         FileResource fr = new FileResource();
         String dna = fr.asString();
         StorageResource genes = storeAll(dna); 
         printGenes(genes);
         findCTG(dna);
         System.out.println("The longest gene found is " + findLongestGene(genes));
         System.out.println("with the length " +  findLongestGene(genes).length());
         System.out.println("The number of genes is " + genes.size());
        }
     public void findCTG(String dna) {
          String DNA = dna.toUpperCase();
          int count = 0;
          int loc = 0;
          int start = 0;
          while(true) {
           loc = DNA.indexOf("CTG",start) ;
           if(loc == -1) {
              break;
            }
            count++;
           start = loc + 3;
        }
        System.out.println("This strand of DNA has " + count + " CTG codons") ;
     }
      public String findLongestGene(StorageResource genes) {
       String longest = "";
       for(String gene : genes.data()) {
           if(longest.length() < gene.length()) {
              longest = gene;
            }
        }
       return longest;
     }
}

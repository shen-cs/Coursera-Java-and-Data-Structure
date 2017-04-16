
/**
 * Write a description of findAllGenes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class findAllGenes {
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
    public void printAll(String dna) {
       String DNA = dna.toUpperCase();
       int start = 0 ;
       System.out.println("DNA string is:");
       System.out.println(DNA);
       System.out.println("Genes found are:");
       while(true) {
           int loc = DNA.indexOf("ATG", start);
           int stopIndex = findStopIndex(DNA, loc + 3);
           if(loc == -1) {
               break;
            }
           else if(stopIndex != -1) {
               System.out.println(DNA.substring(loc, stopIndex + 3));
              start = stopIndex + 3;
            }
           else if(stopIndex == -1) {
              start = loc + 3;
            }
        }
    }
}

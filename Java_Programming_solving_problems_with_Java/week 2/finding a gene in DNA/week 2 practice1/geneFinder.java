
/**
 * Write a description of geneFinder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class geneFinder {
       public String findGene(String dna) {
           String DNA = dna.toUpperCase();
           int start = DNA.indexOf("ATG") - 3;
           int stop = 0;
           String gene;
           while(stop != -1) {
               start = DNA.indexOf("ATG", start + 3);
               stop = DNA.indexOf("TAG", start + 3);
               while((stop - start) % 3 != 0) {
                  stop = DNA.indexOf("TAG", stop + 3);
                  if(stop == -1) {
                      break;
                    }
              }
              if((stop - start) % 3 == 0 && stop != -1) {
                      break;
                    }
            }
           if(stop == -1) {
              gene = "";
            }
            else {
              gene = DNA.substring(start, stop + 3);
            }
           return gene;
        }
      public int test(String data) {
             return data.indexOf("abc",18);
        }
}

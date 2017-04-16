
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class CodonCount {
      private HashMap<String, Integer> map = new HashMap<String, Integer>();
      private void BuildCodonMap(int start, String dna) {
           map.clear();
           String DNA = dna.toUpperCase();
           for(int i = start; i + 3 <= DNA.length() ; i += 3) {
              String codon = DNA.substring(i, i + 3);
              if(map.containsKey(codon)) {
                  map.put(codon, map.get(codon) + 1);
              }
              else {
                 map.put(codon, 1);
              }
           }
      }
      private String getMostCommonCodon() {
          int max = 0;
          String commonCodon = "";
          for(String codon : map.keySet()) {
             if(max < map.get(codon)) {
                max = map.get(codon);
                commonCodon = codon;
             }
          }
          return commonCodon;
       }
      private void printCodonCounts(int start, int end) {
          System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");  
          for(String s : map.keySet()) {
             if(start <= map.get(s) && map.get(s) <= end) {
                 System.out.println(s + "\t" + map.get(s));   
             }  
          }
      }
      public void tester() {
          FileResource fr = new FileResource();
          String dna = fr.asString().trim();
          for(int start = 0; start < 3; start++) {
              BuildCodonMap(start, dna);
              int uniqueness = map.size();
              String commonCodon = getMostCommonCodon();
              int count = map.get(commonCodon);
              System.out.println("Reading frame starting with " + start + " results in " + uniqueness + " unique codons");
              System.out.println("and most common codon is " + commonCodon + " with count " + count);
              printCodonCounts(1, 7);
          }
          System.out.println("--------------------------------------------------------");
      }
}

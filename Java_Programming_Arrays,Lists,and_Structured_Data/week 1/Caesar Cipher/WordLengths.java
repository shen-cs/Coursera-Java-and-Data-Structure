
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordLengths {
     public void testCountWordLength() {
          FileResource fr = new FileResource();
          int[] counts = new int[31];
          countWordLengths(fr, counts);
          int maxIdx = indexOfMax(counts);
          for(int i = 0; i < counts.length; i++) {
             if(i > 0) {
                System.out.println("Length " + i + " : " + counts[i]);
             }
          }
          System.out.println("The most common word length : " + maxIdx);
          System.out.println("-----------------------------------------");
     }
     private void countWordLengths(FileResource resource, int[] counts) {
           for(String w : resource.words()) {
              StringBuilder newWord = new StringBuilder(w);
              if(!Character.isLetter(newWord.charAt(0))) {
                 newWord.deleteCharAt(0);
              }
              if(newWord.length() > 0 && !Character.isLetter(newWord.charAt(newWord.length() - 1))) {
                 newWord.deleteCharAt(newWord.length() - 1); 
              }
              int length = newWord.length();
              if(length >= counts.length) {
                 length = counts.length - 1;
              }
              counts[length]++;
           }
     }
     private int indexOfMax(int[] values) {
         int maxIdx = 0;
         int max = 0;
         for(int i = 0 ; i < values.length; i++) {
            if(max < values[i]) {
                maxIdx = i;
                max = values[i];
            }
         }
         return maxIdx;
     }
}

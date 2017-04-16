
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class WordFrequencies {
        private ArrayList<String> myWords = new ArrayList<String>();
        private ArrayList<Integer> myFreqs = new ArrayList<Integer>();
         private void findUnique() {
           FileResource fr = new FileResource();
           myWords.clear();
           myFreqs.clear();
           for(String w : fr.words()) {
              w = w.toLowerCase();
              StringBuilder word = new StringBuilder(w);
              if(!Character.isLetter(word.charAt(0))) {
                  word.deleteCharAt(0);  
              }
              if(word.length() != 0 && !Character.isLetter(word.charAt(word.length() - 1))) {
                  word.deleteCharAt(word.length() - 1);  
              }
              w = word.toString();
              int index = myWords.indexOf(w);
              if(index != -1) {
                 myFreqs.set(index, myFreqs.get(index) + 1);
              }
              else {
                 myWords.add(w);
                 myFreqs.add(1);
              }
           }
        }
        private int findIndexOfMax() {
           int maxIdx = 0;
           for(int i = 0; i < myFreqs.size(); i++) {
              if(myFreqs.get(maxIdx) < myFreqs.get(i)) {
                 maxIdx = i;
              }
           }
           return maxIdx;
        }
        public WordFrequencies() {
        }       
        public void tester() {
           findUnique();
           System.out.println("Number of unique words: " + myWords.size());
           for(int i = 0; i < myFreqs.size(); i++) {
               System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
           }
           System.out.print("The word that occurs most often and its count are: ");
           System.out.println(myWords.get(findIndexOfMax()) + " " + myFreqs.get(findIndexOfMax()));
        }
}

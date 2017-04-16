
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
     public int compare(QuakeEntry q1, QuakeEntry q2) {
         String word1 = lastWord(q1);
         String word2 = lastWord(q2);
         if(word1.compareTo(word2) > 0) {
            return 1;
         }
         else if(word1.compareTo(word2) < 0) {
           return -1;
         }
         return Double.compare(q1.getMagnitude(), q2.getMagnitude());
     }
     private String lastWord(QuakeEntry qe) {
        String[] words = qe.getInfo().split("\\W");
        return words[words.length - 1];
     }
}


/**
 * Write a description of CaeserBreakerTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarBreakerTwo {
    public String decryptTwoKeys(String input, int key1, int key2) {
        String s1 = halfOfString(input, 0);
        String s2 = halfOfString(input, 1); 
        CaesarCipherTwo cc = new CaesarCipherTwo();
        return cc.encryptTwoKeys(input , 26 - key1, 26 - key2);
    }
    public String decryptTwoKeysWithout(String input) {
        String s1 = halfOfString(input, 0);
        String s2 = halfOfString(input, 1);
        int key1 = getKey(s1), key2 = getKey(s2);
        System.out.println("key1 is " + key1 + "   " + "key2 is " + key2);
        CaesarCipherTwo cct = new CaesarCipherTwo();
        return cct.encryptTwoKeys(input , 26 - key1, 26 - key2);
     }
    private String halfOfString(String message, int start) {
        StringBuilder newStg = new StringBuilder(message);
        StringBuilder s = new StringBuilder();
        for(int i = 0 ; i < message.length(); i++) {
           if(i % 2 == start) {
            s.append(newStg.charAt(i));
           }
        }
        return s.toString();
    }
    private int getKey(String s) {
       int[] freq = countLetterOf(s);
       int maxIdx = maxIndexOf(freq);
       int dkey = maxIdx - 4;
       dkey = (dkey < 0) ? (26 + dkey) : dkey;
       return dkey;
    }
    private int[] countLetterOf(String input) {
       int[] counts = new int[26];
       String newStg = input.toUpperCase();
       for(int i = 0 ; i < newStg.length(); i++) {
          if(Character.isLetter(newStg.charAt(i))) {
             counts[alphabet.indexOf(newStg.charAt(i))]++;
          } 
        }
       return counts;
    }
    private int maxIndexOf(int[] counts) {
       int maxIdx = 0;
       for(int i = 0; i < counts.length; i++) {
           if(counts[maxIdx] < counts[i]) {
               maxIdx = i;
           }
       }
       return maxIdx;
    }
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
}

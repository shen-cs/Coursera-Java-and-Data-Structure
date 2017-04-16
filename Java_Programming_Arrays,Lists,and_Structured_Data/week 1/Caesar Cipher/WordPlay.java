
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
//import edu.duke.*;
public class WordPlay {
     public boolean isVowel(char ch) {
        char newCh = Character.toLowerCase(ch);
        String vowels = "aeiou";
        for(int i = 0; i < vowels.length(); i++) {
          int index = vowels.indexOf(newCh);
          if(index != -1) {
             return true;
          }
        }
        return false;
     }
     public String replaceVowels(String phrase, char ch) {
        StringBuilder newStg = new StringBuilder(phrase);
        for(int i = 0; i < newStg.length(); i++) {
           if(isVowel(newStg.charAt(i))) {
            newStg.setCharAt(i, ch);
           }
        }
        return newStg.toString();
     }
     public String emphasize(String phrase, char ch) {
        StringBuilder newStg = new StringBuilder(phrase);
        for(int i = 0; i < newStg.length(); i++) {
           if(newStg.charAt(i) == ch) {
              if(i % 2 == 0) {
                 newStg.setCharAt(i, '*');  
              } 
              else {
                 newStg.setCharAt(i, '+');  
              }
           }
        }
        return newStg.toString();
     }
}

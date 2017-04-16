
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipherTwo {
    public String encryptTwoKeys(String input, int key1, int key2) {
       StringBuilder newStg = new StringBuilder(input);
       CaesarCipher cc1 = new CaesarCipher(key1);
       CaesarCipher cc2 = new CaesarCipher(key2);
       String s1 = halfOfString(input, 0);
       String s2 = halfOfString(input, 1);
       StringBuilder stg1 = new StringBuilder(cc1.encrypt(s1));
       StringBuilder stg2 = new StringBuilder(cc2.encrypt(s2));
       for(int i = 0; i < newStg.length(); i++) {
          if(i % 2 == 0 && i / 2 < newStg.length() / 2) {
             stg1.insert(i + 1, stg2.charAt(i / 2));
          }
       }
      // System.out.println(stg1.toString());
       return stg1.toString();
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
}
